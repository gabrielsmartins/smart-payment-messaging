package br.gabrielsmartins.smartpayment.adapters.messaging.mapper.out.completed;

import br.gabrielsmartins.schemas.order_completed.OrderCompleted;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.OrderItem;
import br.gabrielsmartins.smartpayment.application.domain.PaymentMethod;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import br.gabrielsmartins.smartpayment.application.domain.enums.PaymentType;
import br.gabrielsmartins.smartpayment.application.domain.state.OrderLog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class CompletedOrderStatusMessagingOutputMapperTest {

    private CompletedOrderStatusMessagingOutputMapper mapper;

    @BeforeEach
    public void setup(){
        var itemMapper = new CompletedOrderItemStatusMessagingOutputMapper();
        var paymentMethodMapper = new CompletedOrderStatusPaymentMethodMessagingOutputMapper();
        this.mapper = new CompletedOrderStatusMessagingOutputMapper(itemMapper, paymentMethodMapper);
    }

    @Test
    @DisplayName("Given Order When Map Then Return Message")
    public void givenOrderWhenMapThenReturnMessage(){

        Order order = Order.builder()
                .withId(UUID.randomUUID().toString())
                .withCustomerId(UUID.randomUUID())
                .withCreatedAt(LocalDateTime.now())
                .withFinishedAt(LocalDateTime.now())
                .withStatus(OrderStatus.COMPLETED)
                .withTotalAmount(BigDecimal.valueOf(1500.00))
                .withTotalDiscount(BigDecimal.valueOf(1400.00))
                .withFinishedAt(LocalDateTime.now())
                .build();

        OrderLog orderLog = OrderLog.builder()
                .withStatus(OrderStatus.COMPLETED)
                .withDatetime(LocalDateTime.now())
                .build();

        order.addLog(orderLog);

        OrderItem item = OrderItem.builder()
                .withProductId(UUID.randomUUID())
                .withQuantity(1)
                .withAmount(BigDecimal.TEN)
                .build();

        order.addItem(item);

        PaymentMethod paymentMethod = PaymentMethod.builder()
                                            .withPaymentType(PaymentType.CASH)
                                            .withAmount(BigDecimal.TEN)
                                            .build();

        order.addPaymentMethod(paymentMethod);


        OrderCompleted message = this.mapper.mapToMessage(order);

        assertThat(message).hasNoNullFieldsOrProperties();
        assertThat(message.getCreatedAt()).isEqualTo(order.getCreatedAt());
        assertThat(message.getCustomerId()).isEqualTo(order.getCustomerId().toString());
        assertThat(message.getTotalAmount()).isEqualTo(order.getTotalAmount());
        assertThat(message.getTotalDiscount()).isEqualTo(order.getTotalDiscount());
        assertThat(message.getItems().size()).isEqualTo(order.getItems().size());
        assertThat(message.getPaymentMethods().size()).isEqualTo(order.getPaymentMethods().size());
    }

    @Test
    @DisplayName("Given Mapper When Get Order Status Then Return Order Status Completed")
    public void givenMapperWhenGetOrderStatusThenReturnOrderStatusCompleted(){
        OrderStatus orderStatus = this.mapper.getOrderStatus();
        assertThat(orderStatus).isEqualTo(OrderStatus.COMPLETED);
    }

}

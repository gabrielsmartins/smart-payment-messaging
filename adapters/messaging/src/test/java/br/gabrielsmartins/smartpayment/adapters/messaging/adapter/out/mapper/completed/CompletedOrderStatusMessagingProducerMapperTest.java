package br.gabrielsmartins.smartpayment.adapters.messaging.adapter.out.mapper.completed;

import br.gabrielsmartins.schemas.order_completed.OrderCompleted;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.OrderItem;
import br.gabrielsmartins.smartpayment.application.domain.PaymentMethod;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import br.gabrielsmartins.smartpayment.application.domain.state.OrderLog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.gabrielsmartins.smartpayment.application.support.OrderItemSupport.defaultOrderItem;
import static br.gabrielsmartins.smartpayment.application.support.OrderLogSupport.defaultOrderLog;
import static br.gabrielsmartins.smartpayment.application.support.OrderSupport.defaultOrder;
import static br.gabrielsmartins.smartpayment.application.support.PaymentMethodSupport.defaultPaymentMethod;
import static org.assertj.core.api.Assertions.assertThat;

public class CompletedOrderStatusMessagingProducerMapperTest {

    private CompletedOrderStatusMessagingProducerMapper mapper;

    @BeforeEach
    public void setup(){
        var itemMapper = new CompletedOrderItemStatusMessagingProducerMapper();
        var paymentMethodMapper = new CompletedOrderStatusPaymentMethodMessagingProducerMapper();
        this.mapper = new CompletedOrderStatusMessagingProducerMapper(itemMapper, paymentMethodMapper);
    }

    @Test
    @DisplayName("Given Order When Map Then Return Message")
    public void givenOrderWhenMapThenReturnMessage(){

        Order order = defaultOrder().build();

        OrderLog orderLog = defaultOrderLog().build();
        order.addLog(orderLog);

        OrderItem item = defaultOrderItem().build();
        order.addItem(item);

        PaymentMethod paymentMethod = defaultPaymentMethod().build();
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

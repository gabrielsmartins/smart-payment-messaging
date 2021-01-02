package br.gabrielsmartins.smartpayment.adapters.web.mapper.out;

import br.gabrielsmartins.smartpayment.adapters.web.adapter.out.dto.NotificationOrderDTO;
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

public class NotificationOrderWebMapperTest {

    private NotificationOrderWebMapper mapper;

    @BeforeEach
    public void setup(){
        var orderItemRequestWebMapper = new NotificationOrderItemWebMapperImpl();
        var paymentMethodRequestWebMapper = new NotificationPaymentMethodWebMapperImpl();
        this.mapper = new NotificationOrderWebMapperImpl(orderItemRequestWebMapper, paymentMethodRequestWebMapper);
    }

    @Test
    @DisplayName("given Order When Map Then Return Notification Dto")
    public void givenOrderWhenMapThenReturnNotificationDto(){

        Order order = Order.builder()
                .withId(UUID.randomUUID().toString())
                .withCustomerId(UUID.randomUUID())
                .withCreatedAt(LocalDateTime.now())
                .withFinishedAt(LocalDateTime.now())
                .withStatus(OrderStatus.COMPLETED)
                .withTotalAmount(BigDecimal.valueOf(1500.00))
                .withTotalDiscount(BigDecimal.valueOf(1400.00))
                .build();

        OrderLog orderLog = OrderLog.builder()
                .withStatus(OrderStatus.COMPLETED)
                .withDatetime(LocalDateTime.now())
                .build();

        order.addLog(orderLog);

        OrderItem item = new OrderItem();
        item.setProductId(UUID.randomUUID());
        item.setQuantity(10);
        item.setAmount(BigDecimal.TEN);

        order.addItem(item);

        PaymentMethod paymentMethod = PaymentMethod.builder()
                .withPaymentType(PaymentType.CASH)
                .withAmount(BigDecimal.TEN)
                .build();

        order.addPaymentMethod(paymentMethod);

        NotificationOrderDTO notificationOrderDTO = this.mapper.mapToDto(order);

        assertThat(notificationOrderDTO.getId()).isEqualTo(order.getId());
        assertThat(notificationOrderDTO.getCustomerId()).isEqualTo(order.getCustomerId());
        assertThat(notificationOrderDTO.getCreatedAt()).isEqualTo(order.getCreatedAt());
        assertThat(notificationOrderDTO.getFinishedAt()).isEqualTo(order.getFinishedAt());
        assertThat(notificationOrderDTO.getTotalAmount()).isEqualTo(order.getTotalAmount());
        assertThat(notificationOrderDTO.getTotalDiscount()).isEqualTo(order.getTotalDiscount());;
        assertThat(notificationOrderDTO.getItems().size()).isEqualTo(order.getItems().size());
        assertThat(notificationOrderDTO.getPaymentMethods().size()).isEqualTo(order.getPaymentMethods().size());
    }
}

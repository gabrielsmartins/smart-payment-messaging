package br.gabrielsmartins.smartpayment.adapters.web.mapper.out;

import br.gabrielsmartins.smartpayment.adapters.web.adapter.out.dto.NotificationOrderDTO;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.OrderItem;
import br.gabrielsmartins.smartpayment.application.domain.PaymentMethod;
import br.gabrielsmartins.smartpayment.application.domain.state.OrderLog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static br.gabrielsmartins.smartpayment.application.support.OrderLogSupport.defaultOrderLog;
import static br.gabrielsmartins.smartpayment.application.support.OrderSupport.defaultOrder;
import static br.gabrielsmartins.smartpayment.application.support.PaymentMethodSupport.defaultPaymentMethod;
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

        Order order = defaultOrder().build();

        OrderLog orderLog = defaultOrderLog().build();

        order.addLog(orderLog);

        OrderItem item = new OrderItem();
        item.setProductId(UUID.randomUUID());
        item.setQuantity(10);
        item.setAmount(BigDecimal.TEN);

        order.addItem(item);

        PaymentMethod paymentMethod = defaultPaymentMethod().build();

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

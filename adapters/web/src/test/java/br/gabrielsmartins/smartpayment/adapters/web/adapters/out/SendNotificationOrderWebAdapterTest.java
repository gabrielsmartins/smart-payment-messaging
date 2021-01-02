package br.gabrielsmartins.smartpayment.adapters.web.adapters.out;

import br.gabrielsmartins.smartpayment.adapters.web.adapter.out.SendNotificationOrderWebAdapter;
import br.gabrielsmartins.smartpayment.adapters.web.adapter.out.NotificationOrderWebClient;
import br.gabrielsmartins.smartpayment.adapters.web.adapter.out.dto.NotificationOrderDTO;
import br.gabrielsmartins.smartpayment.adapters.web.mapper.out.*;
import br.gabrielsmartins.smartpayment.adapters.web.mapper.out.NotificationOrderWebMapper;
import br.gabrielsmartins.smartpayment.adapters.web.mapper.out.NotificationOrderWebMapperImpl;
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

import static org.mockito.Mockito.*;

public class SendNotificationOrderWebAdapterTest {

    private SendNotificationOrderWebAdapter adapter;
    private NotificationOrderWebClient client;
    private NotificationOrderWebMapper mapper;

    @BeforeEach
    public void setup(){
        this.client = mock(NotificationOrderWebClient.class);
        var orderItemRequestWebMapper = new NotificationOrderItemWebMapperImpl();
        var paymentMethodRequestWebMapper = new NotificationPaymentMethodWebMapperImpl();
        this.mapper = new NotificationOrderWebMapperImpl(orderItemRequestWebMapper, paymentMethodRequestWebMapper);
        this.adapter = new SendNotificationOrderWebAdapter(client, mapper);
    }

    @Test
    @DisplayName("Given Order When Send Then Call Client")
    public void givenOrderWhenSendThenCallClient(){
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


        this.adapter.send(order);

        verify(this.client, times(1)).submit(any(NotificationOrderDTO.class));
    }

}

package br.gabrielsmartins.smartpayment.adapters.web.adapters.out;

import br.gabrielsmartins.smartpayment.adapters.web.adapter.out.NotificationOrderWebClient;
import br.gabrielsmartins.smartpayment.adapters.web.adapter.out.SendNotificationOrderWebAdapter;
import br.gabrielsmartins.smartpayment.adapters.web.adapter.out.dto.NotificationOrderDTO;
import br.gabrielsmartins.smartpayment.adapters.web.mapper.out.NotificationOrderItemWebMapperImpl;
import br.gabrielsmartins.smartpayment.adapters.web.mapper.out.NotificationOrderWebMapper;
import br.gabrielsmartins.smartpayment.adapters.web.mapper.out.NotificationOrderWebMapperImpl;
import br.gabrielsmartins.smartpayment.adapters.web.mapper.out.NotificationPaymentMethodWebMapperImpl;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.OrderItem;
import br.gabrielsmartins.smartpayment.application.domain.PaymentMethod;
import br.gabrielsmartins.smartpayment.application.domain.state.OrderLog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.gabrielsmartins.smartpayment.application.support.OrderItemSupport.defaultOrderItem;
import static br.gabrielsmartins.smartpayment.application.support.OrderLogSupport.defaultOrderLog;
import static br.gabrielsmartins.smartpayment.application.support.OrderSupport.defaultOrder;
import static br.gabrielsmartins.smartpayment.application.support.PaymentMethodSupport.defaultPaymentMethod;
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
        Order order = defaultOrder().build();

        OrderLog orderLog = defaultOrderLog().build();

        order.addLog(orderLog);

        OrderItem item = defaultOrderItem().build();

        order.addItem(item);

        PaymentMethod paymentMethod = defaultPaymentMethod().build();

        order.addPaymentMethod(paymentMethod);


        this.adapter.send(order);

        verify(this.client, times(1)).submit(any(NotificationOrderDTO.class));
    }

}

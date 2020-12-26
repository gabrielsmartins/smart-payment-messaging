package br.gabrielsmartins.smartpayment.adapters.web.adapters.out;

import br.gabrielsmartins.smartpayment.adapters.web.adapter.out.SendOrderWebAdapter;
import br.gabrielsmartins.smartpayment.adapters.web.adapter.out.SendOrderWebClient;
import br.gabrielsmartins.smartpayment.adapters.web.adapter.out.dto.OrderRequestDTO;
import br.gabrielsmartins.smartpayment.adapters.web.mapper.out.OrderRequestMapper;
import br.gabrielsmartins.smartpayment.adapters.web.mapper.out.OrderRequestMapperImpl;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import br.gabrielsmartins.smartpayment.application.domain.enums.PaymentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.Mockito.*;

public class SendOrderWebAdapterTest {

    private SendOrderWebAdapter adapter;
    private SendOrderWebClient client;
    private OrderRequestMapper mapper;

    @BeforeEach
    public void setup(){
        this.client = mock(SendOrderWebClient.class);
        this.mapper = new OrderRequestMapperImpl();
        this.adapter = new SendOrderWebAdapter(client, mapper);
    }

    @Test
    @DisplayName("Given Order When Send Then Call Client")
    public void givenOrderWhenSendThenCallClient(){
        Order order = Order.builder()
                .withId(UUID.randomUUID().toString())
                .withCustomerId(UUID.randomUUID().toString())
                .withCreatedAt(LocalDateTime.now())
                .withFinishedAt(LocalDateTime.now())
                .withStatus(OrderStatus.COMPLETED)
                .withTotalAmount(BigDecimal.valueOf(1500.00))
                .withTotalDiscount(BigDecimal.valueOf(1400.00))
                .build();

        order.addLog(LocalDateTime.now(), OrderStatus.REQUESTED);
        order.addItem(UUID.randomUUID().toString(), BigDecimal.ZERO);
        order.addPaymentMethod(PaymentType.CREDIT_CARD, BigDecimal.TEN);

        this.adapter.send(order);

        verify(this.client, times(1)).submit(any(OrderRequestDTO.class));
    }

}

package br.gabrielsmartins.smartpayment.messaging.application.service.orders;

import br.gabrielsmartins.smartpayment.messaging.application.domain.enums.PaymentType;
import br.gabrielsmartins.smartpayment.messaging.application.domain.orders.Order;
import br.gabrielsmartins.smartpayment.messaging.application.domain.orders.Order.OrderPaymentMethod;
import br.gabrielsmartins.smartpayment.messaging.application.ports.out.orders.SaveOrderPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SaveOrderServiceTest {

    private SaveOrderService service;
    private SaveOrderPort port;

    @BeforeEach
    public void setup(){
        this.port = mock(SaveOrderPort.class);
        this.service = new SaveOrderService(port);
    }

    @Test
    @DisplayName("Given Order When Save Then Return Saved Order")
    public void givenOrderWhenSaveThenReturnSavedOrder(){

        OrderPaymentMethod paymentMethod = OrderPaymentMethod.builder()
                                                        .withDiscount(new BigDecimal(100.00))
                                                        .withTotalAmountPaid(new BigDecimal(1400.00))
                                                        .withPaymentType(PaymentType.CASH)
                                                        .build();

        Order order = Order.builder()
                               .withPaymentNumberIdentifier(UUID.randomUUID().toString())
                               .withDueDate(LocalDate.now())
                               .withPaymentDate(LocalDate.now())
                               .withTotalAmount(new BigDecimal(1500.00))
                               .withTotalAmountPaid(new BigDecimal(1400.00))
                               .build();

        order.addPaymentMethod(paymentMethod);

        when(port.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Order savedOrder = service.save(order);
        assertThat(savedOrder).isNotNull();
    }
}

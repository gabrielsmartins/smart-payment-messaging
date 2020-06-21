package br.gabrielsmartins.smartpayment.messaging.application.service.payments;

import br.gabrielsmartins.smartpayment.messaging.application.domain.enums.PaymentType;
import br.gabrielsmartins.smartpayment.messaging.application.domain.payments.Payment;
import br.gabrielsmartins.smartpayment.messaging.application.domain.payments.Payment.PaymentMethod;
import br.gabrielsmartins.smartpayment.messaging.application.ports.out.payments.SavePaymentPort;
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

public class SavePaymentServiceTest {

    private SavePaymentService service;
    private SavePaymentPort port;

    @BeforeEach
    public void setup(){
        this.port = mock(SavePaymentPort.class);
        this.service = new SavePaymentService(port);
    }

    @Test
    @DisplayName("Given Payment When Save Then Return Saved Payment")
    public void givenPaymentWhenSaveThenReturnSavedPayment(){

        PaymentMethod paymentMethod = PaymentMethod.builder()
                                                    .withDiscount(new BigDecimal(100.00))
                                                    .withTotalAmountPaid(new BigDecimal(1400.00))
                                                    .withPaymentType(PaymentType.CREDIT_CARD)
                                                    .build();

        Payment payment = Payment.builder()
                                .withPaymentNumberIdentifier(UUID.randomUUID().toString())
                                .withDueDate(LocalDate.now())
                                .withPaymentDate(LocalDate.now())
                                .withTotalAmount(new BigDecimal(1500.00))
                                .withTotalAmountPaid(new BigDecimal(1400.00))
                                .build();

        payment.addPaymentMethod(paymentMethod);

        when(port.save(any(Payment.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Payment savedPayment = service.save(payment);
        assertThat(savedPayment).isNotNull();
    }
}

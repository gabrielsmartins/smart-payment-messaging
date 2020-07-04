package br.gabrielsmartins.smartpayment.messaging.adapters.persistence.adapter.responses;

import br.gabrielsmartins.smartpayment.messaging.adapters.persistence.adapter.payments.SavePaymentPersistenceAdapter;
import br.gabrielsmartins.smartpayment.messaging.adapters.persistence.entity.payments.PaymentEntity;
import br.gabrielsmartins.smartpayment.messaging.adapters.persistence.mapper.payments.PaymentPersistenceMapper;
import br.gabrielsmartins.smartpayment.messaging.adapters.persistence.mapper.payments.PaymentPersistenceMapper$PaymentMethodPersistenceMapperImpl;
import br.gabrielsmartins.smartpayment.messaging.adapters.persistence.mapper.payments.PaymentPersistenceMapperImpl;
import br.gabrielsmartins.smartpayment.messaging.adapters.persistence.service.payments.SavePaymentPersistenceService;
import br.gabrielsmartins.smartpayment.messaging.application.domain.enums.PaymentType;
import br.gabrielsmartins.smartpayment.messaging.application.domain.payments.Payment;
import br.gabrielsmartins.smartpayment.messaging.application.domain.payments.Payment.PaymentMethod;
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

public class SavePaymentPersistenceAdapterTest {

    private SavePaymentPersistenceAdapter adapter;
    private SavePaymentPersistenceService service;
    private PaymentPersistenceMapper mapper;

    @BeforeEach
    public void setup(){
        this.service = mock(SavePaymentPersistenceService.class);
        PaymentPersistenceMapper.PaymentMethodPersistenceMapper paymentMethodPersistenceMapper = new PaymentPersistenceMapper$PaymentMethodPersistenceMapperImpl();
        this.mapper = new PaymentPersistenceMapperImpl(paymentMethodPersistenceMapper);
        this.adapter = new SavePaymentPersistenceAdapter(service,mapper);
    }

    @Test
    @DisplayName("Given Payment When Save Then Return Saved Payment")
    public void givenPaymentWhenSaveThenReturnSavedPayment(){

        Payment payment = Payment.builder()
                                .withId(UUID.randomUUID().toString())
                                .withPaymentNumberIdentifier(UUID.randomUUID().toString())
                                .withDueDate(LocalDate.now())
                                .withPaymentDate(LocalDate.now())
                                .withTotalAmount(new BigDecimal(1500.00))
                                .withTotalAmountPaid(new BigDecimal(1400.00))
                                .build();

        PaymentMethod paymentMethod = PaymentMethod.builder()
                                                .withId(1L)
                                                .withDiscount(new BigDecimal(100.00))
                                                .withTotalAmountPaid(new BigDecimal(1400.00))
                                                .withPaymentType(PaymentType.CREDIT_CARD)
                                                .build();

        payment.addPaymentMethod(paymentMethod);

        when(service.save(any(PaymentEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Payment savedPayment = adapter.save(payment);
        assertThat(savedPayment).isNotNull();
    }

}

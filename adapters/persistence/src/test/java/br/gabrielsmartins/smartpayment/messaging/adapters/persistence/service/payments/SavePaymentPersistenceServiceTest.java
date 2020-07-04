package br.gabrielsmartins.smartpayment.messaging.adapters.persistence.service.payments;

import br.gabrielsmartins.smartpayment.messaging.adapters.persistence.entity.payments.PaymentEntity;
import br.gabrielsmartins.smartpayment.messaging.adapters.persistence.entity.payments.PaymentEntity.PaymentMethodEntity;
import br.gabrielsmartins.smartpayment.messaging.adapters.persistence.repository.payments.PaymentRepository;
import br.gabrielsmartins.smartpayment.messaging.application.domain.enums.PaymentType;
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

public class SavePaymentPersistenceServiceTest {

    private SavePaymentPersistenceService service;
    private PaymentRepository repository;

    @BeforeEach
    public void setup(){
        this.repository = mock(PaymentRepository.class);
       this.service = new SavePaymentPersistenceService(repository);
    }

    @Test
    @DisplayName("Given Payment When Save Then Return Saved Payment")
    public void givenPaymentWhenSaveThenReturnSavedPayment(){
        PaymentMethodEntity paymentMethod = PaymentMethodEntity.builder()
                                                .withDiscount(new BigDecimal(100.00))
                                                .withTotalAmountPaid(new BigDecimal(1400.00))
                                                .withPaymentType(PaymentType.CREDIT_CARD)
                                                .build();

        PaymentEntity paymentEntity = PaymentEntity.builder()
                                    .withId(UUID.randomUUID().toString())
                                    .withPaymentNumberIdentifier(UUID.randomUUID().toString())
                                    .withDueDate(LocalDate.now())
                                    .withPaymentDate(LocalDate.now())
                                    .withTotalAmount(new BigDecimal(1500.00))
                                    .withTotalAmountPaid(new BigDecimal(1400.00))
                                    .build();

        paymentEntity.addPaymentMethod(paymentMethod);

        when(repository.save(any(PaymentEntity.class))).then(invocation -> invocation.getArgument(0));

        PaymentEntity savedPayment = this.service.save(paymentEntity);
        assertThat(savedPayment).isNotNull();
    }
}

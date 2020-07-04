package br.gabrielsmartins.smartpayment.messaging.adapters.persistence.mapper.payments;

import br.gabrielsmartins.smartpayment.messaging.adapters.persistence.entity.orders.OrderEntity;
import br.gabrielsmartins.smartpayment.messaging.adapters.persistence.entity.payments.PaymentEntity;
import br.gabrielsmartins.smartpayment.messaging.adapters.persistence.entity.payments.PaymentEntity.PaymentMethodEntity;
import br.gabrielsmartins.smartpayment.messaging.application.domain.enums.PaymentType;
import br.gabrielsmartins.smartpayment.messaging.application.domain.orders.Order;
import br.gabrielsmartins.smartpayment.messaging.application.domain.payments.Payment;
import br.gabrielsmartins.smartpayment.messaging.application.domain.payments.Payment.PaymentMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


public class PaymentPersistenceMapperTest {

    private PaymentPersistenceMapper mapper;
    private PaymentPersistenceMapper.PaymentMethodPersistenceMapper paymentMethodPersistenceMapper;


    @BeforeEach
    public void setup(){
        this.paymentMethodPersistenceMapper = new PaymentPersistenceMapper$PaymentMethodPersistenceMapperImpl();
        this.mapper = new PaymentPersistenceMapperImpl(paymentMethodPersistenceMapper);
    }

    @Test
    @DisplayName("Given Payment Entity When Map Then Return Payment Domain")
    public void givenPaymentEntityWhenMapThenReturnPaymentDomain(){

        Payment payment = Payment.builder()
                                .withOrder(new Order())
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

        PaymentEntity paymentEntity = this.mapper.mapToEntity(payment);

        assertThat(paymentEntity.getOrder()).isNotNull();
        assertThat(paymentEntity.getPaymentNumberIdentifier()).isEqualTo(payment.getPaymentNumberIdentifier());
        assertThat(paymentEntity.getDueDate()).isEqualTo(payment.getDueDate());
        assertThat(paymentEntity.getPaymentDate()).isEqualTo(payment.getPaymentDate());
        assertThat(paymentEntity.getTotalAmount()).isEqualTo(payment.getTotalAmount());
        assertThat(paymentEntity.getTotalAmountPaid()).isEqualTo(payment.getTotalAmountPaid());
        assertThat(paymentEntity.getPaymentMethods().size()).isEqualTo(payment.getPaymentMethods().size());
    }

    @Test
    @DisplayName("Given Payment Domain When Map Then Return Payment Entity")
    public void givenPaymentDomainWhenMapThenReturnPaymentEntity(){

        PaymentEntity paymentEntity = PaymentEntity.builder()
                                                    .withOrder(new OrderEntity())
                                                    .withPaymentNumberIdentifier(UUID.randomUUID().toString())
                                                    .withDueDate(LocalDate.now())
                                                    .withPaymentDate(LocalDate.now())
                                                    .withTotalAmount(new BigDecimal(1500.00))
                                                    .withTotalAmountPaid(new BigDecimal(1400.00))
                                                    .build();

        PaymentMethodEntity paymentMethod = PaymentMethodEntity.builder()
                                                                .withId(1L)
                                                                .withPayment(paymentEntity)
                                                                .withDiscount(new BigDecimal(100.00))
                                                                .withTotalAmountPaid(new BigDecimal(1400.00))
                                                                .withPaymentType(PaymentType.CREDIT_CARD)
                                                                .build();

        paymentEntity.addPaymentMethod(paymentMethod);

        Payment payment = this.mapper.mapToDomain(paymentEntity);

        assertThat(payment.getOrder()).isNotNull();
        assertThat(payment.getPaymentNumberIdentifier()).isEqualTo(paymentEntity.getPaymentNumberIdentifier());
        assertThat(payment.getDueDate()).isEqualTo(paymentEntity.getDueDate());
        assertThat(payment.getPaymentDate()).isEqualTo(paymentEntity.getPaymentDate());
        assertThat(payment.getTotalAmount()).isEqualTo(paymentEntity.getTotalAmount());
        assertThat(payment.getTotalAmountPaid()).isEqualTo(paymentEntity.getTotalAmountPaid());
        assertThat(payment.getPaymentMethods().size()).isEqualTo(paymentEntity.getPaymentMethods().size());
    }
}

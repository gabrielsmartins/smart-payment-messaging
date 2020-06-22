package br.gabrielsmartins.smartpayment.messaging.adapters.persistence.repository;

import br.gabrielsmartins.smartpayment.messaging.adapters.persistence.entity.OrderEntity;
import br.gabrielsmartins.smartpayment.messaging.adapters.persistence.entity.OrderEntity.OrderPaymentMethodEntity;
import br.gabrielsmartins.smartpayment.messaging.application.domain.enums.PaymentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderRepositoryTest {

    private OrderRepository repository;

    @BeforeEach
    public void setup(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("smartpayment-PU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        this.repository = new OrderRepository(entityManager);
    }

    @Test
    @DisplayName("Given Order When Save Then Return Saved Order")
    public void givenOrderWhenSaveThenReturnSavedOrder(){
        OrderPaymentMethodEntity paymentMethod = OrderPaymentMethodEntity.builder()
                .withDiscount(new BigDecimal(100.00))
                .withTotalAmountPaid(new BigDecimal(1400.00))
                .withPaymentType(PaymentType.CREDIT_CARD)
                .build();

        OrderEntity orderEntity = OrderEntity.builder()
                .withId(UUID.randomUUID().toString())
                .withPaymentNumberIdentifier(UUID.randomUUID().toString())
                .withDueDate(LocalDate.now())
                .withPaymentDate(LocalDate.now())
                .withTotalAmount(new BigDecimal(1500.00))
                .withTotalAmountPaid(new BigDecimal(1400.00))
                .build();

        orderEntity.addPaymentMethod(paymentMethod);


        OrderEntity savedOrder = this.repository.save(orderEntity);
        assertThat(savedOrder).isNotNull();
    }
}

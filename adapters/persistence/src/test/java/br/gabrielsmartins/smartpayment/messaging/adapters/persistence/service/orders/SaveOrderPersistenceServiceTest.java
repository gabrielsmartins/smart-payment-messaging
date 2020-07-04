package br.gabrielsmartins.smartpayment.messaging.adapters.persistence.service.orders;

import br.gabrielsmartins.smartpayment.messaging.adapters.persistence.entity.orders.OrderEntity;
import br.gabrielsmartins.smartpayment.messaging.adapters.persistence.entity.orders.OrderEntity.OrderPaymentMethodEntity;
import br.gabrielsmartins.smartpayment.messaging.adapters.persistence.repository.orders.OrderRepository;
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

public class SaveOrderPersistenceServiceTest {

    private SaveOrderPersistenceService service;
    private OrderRepository repository;

    @BeforeEach
    public void setup(){
        this.repository = mock(OrderRepository.class);
       this.service = new SaveOrderPersistenceService(repository);
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

        when(repository.save(any(OrderEntity.class))).then(invocation -> invocation.getArgument(0));

        OrderEntity savedOrder = this.service.save(orderEntity);
        assertThat(savedOrder).isNotNull();
    }
}

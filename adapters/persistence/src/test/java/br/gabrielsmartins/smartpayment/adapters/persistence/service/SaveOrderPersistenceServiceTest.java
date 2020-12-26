package br.gabrielsmartins.smartpayment.adapters.persistence.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.OrderEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.repository.OrderRepository;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import br.gabrielsmartins.smartpayment.application.domain.enums.PaymentType;

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
    	OrderEntity orderEntity = OrderEntity.builder()
                .withId(UUID.randomUUID().toString())
                .withCustomerId(UUID.randomUUID().toString())
                .withCreatedAt(LocalDateTime.now())
                .withFinishedAt(LocalDateTime.now())
                .withStatus(OrderStatus.COMPLETED)
                .withTotalAmount(new BigDecimal(1500.00))
                .withTotalDiscount(new BigDecimal(1400.00))
                .build();

		orderEntity.addLog(LocalDateTime.now(), OrderStatus.REQUESTED);
		orderEntity.addItem(UUID.randomUUID().toString(), BigDecimal.ZERO);
		orderEntity.addPaymentMethod(PaymentType.CREDIT_CARD, BigDecimal.TEN);

        when(repository.save(any(OrderEntity.class))).then(invocation -> invocation.getArgument(0));

        OrderEntity savedOrder = this.service.save(orderEntity);
        assertThat(savedOrder).isNotNull();
    }
}

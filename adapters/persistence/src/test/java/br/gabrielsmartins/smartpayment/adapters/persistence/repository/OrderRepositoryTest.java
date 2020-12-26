package br.gabrielsmartins.smartpayment.adapters.persistence.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.OrderEntity;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import br.gabrielsmartins.smartpayment.application.domain.enums.PaymentType;

@ExtendWith(SpringExtension.class)
@DataMongoTest
@ActiveProfiles("test")
public class OrderRepositoryTest {

	@Autowired
	private OrderRepository repository;

	@Test
	@DisplayName("Given Order When Save Then Return Saved Order")
	public void givenOrderWhenSaveThenReturnSavedOrder() {
		OrderEntity orderEntity = OrderEntity.builder(
				).withId(UUID.randomUUID().toString())
				.withCustomerId(UUID.randomUUID().toString())
				.withCreatedAt(LocalDateTime.now())
				.withFinishedAt(LocalDateTime.now())
				.withStatus(OrderStatus.COMPLETED)
				.withTotalAmount(new BigDecimal(1500.00))
				.withTotalDiscount(new BigDecimal(1400.00)).build();

		orderEntity.addLog(LocalDateTime.now(), OrderStatus.REQUESTED);
		orderEntity.addItem(UUID.randomUUID().toString(), BigDecimal.ZERO);
		orderEntity.addPaymentMethod(PaymentType.CREDIT_CARD, BigDecimal.TEN);

		OrderEntity savedOrder = this.repository.save(orderEntity);
		assertThat(savedOrder).isNotNull();
	}
}

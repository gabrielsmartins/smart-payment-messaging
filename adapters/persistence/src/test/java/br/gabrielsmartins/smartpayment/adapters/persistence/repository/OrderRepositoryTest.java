package br.gabrielsmartins.smartpayment.adapters.persistence.repository;

import br.gabrielsmartins.smartpayment.adapters.persistence.config.DatabaseConfiguration;
import br.gabrielsmartins.smartpayment.adapters.persistence.entity.OrderEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.entity.OrderItemEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.entity.OrderLogEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.entity.PaymentMethodEntity;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import br.gabrielsmartins.smartpayment.application.domain.enums.PaymentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataMongoTest
@Import(DatabaseConfiguration.class)
@ActiveProfiles("test")
public class OrderRepositoryTest {

	@Autowired
	private OrderRepository repository;

	@Test
	@DisplayName("Given Order When Save Then Return Saved Order")
	public void givenOrderWhenSaveThenReturnSavedOrder() {

		OrderEntity orderEntity = OrderEntity.builder()
											.withCustomerId(UUID.randomUUID())
											.withCreatedAt(LocalDateTime.now())
											.withFinishedAt(LocalDateTime.now())
											.withStatus(OrderStatus.COMPLETED)
											.withTotalAmount(BigDecimal.valueOf(1500.00))
											.withTotalDiscount(BigDecimal.valueOf(1400.00))
											.build();

		OrderLogEntity log = OrderLogEntity.builder()
											.withStatus(OrderStatus.COMPLETED)
											.withDatetime(LocalDateTime.now())
											.build();

		orderEntity.addLog(log);

		OrderItemEntity item = OrderItemEntity.builder()
											.withProductId(UUID.randomUUID())
											.withQuantity(1)
											.withAmount(BigDecimal.TEN)
											.build();

		orderEntity.addItem(item);

		PaymentMethodEntity paymentMethod = PaymentMethodEntity.builder()
															.withPaymentType(PaymentType.CASH.getDescription())
															.withAmount(BigDecimal.TEN)
															.build();

		orderEntity.addPaymentMethod(paymentMethod);

		OrderEntity savedOrder = this.repository.save(orderEntity);
		assertThat(savedOrder).isNotNull();
	}
}

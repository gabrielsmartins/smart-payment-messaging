package br.gabrielsmartins.smartpayment.adapters.persistence.repository;

import br.gabrielsmartins.smartpayment.adapters.persistence.config.DatabaseConfiguration;
import br.gabrielsmartins.smartpayment.adapters.persistence.entity.OrderEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.entity.OrderItemEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.entity.OrderLogEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.entity.PaymentMethodEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static br.gabrielsmartins.smartpayment.adapters.persistence.support.OrderEntitySupport.defaultOrderEntity;
import static br.gabrielsmartins.smartpayment.adapters.persistence.support.OrderItemEntitySupport.defaultOrderItemEntity;
import static br.gabrielsmartins.smartpayment.adapters.persistence.support.OrderLogEntitySupport.defaultOrderLogEntity;
import static br.gabrielsmartins.smartpayment.adapters.persistence.support.PaymentMethodEntitySupport.defaultPaymentMethodEntity;
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

		OrderEntity orderEntity = defaultOrderEntity().build();

		OrderLogEntity log = defaultOrderLogEntity().build();

		orderEntity.addLog(log);

		OrderItemEntity item = defaultOrderItemEntity().build();

		orderEntity.addItem(item);

		PaymentMethodEntity paymentMethod = defaultPaymentMethodEntity().build();

		orderEntity.addPaymentMethod(paymentMethod);

		OrderEntity savedOrder = this.repository.save(orderEntity);
		assertThat(savedOrder).isNotNull();
	}
}

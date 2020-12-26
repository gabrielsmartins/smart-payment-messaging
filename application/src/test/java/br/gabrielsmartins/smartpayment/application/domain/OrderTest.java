package br.gabrielsmartins.smartpayment.application.domain;

import br.gabrielsmartins.smartpayment.application.domain.Order.OrderItem;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import br.gabrielsmartins.smartpayment.application.domain.enums.PaymentType;
import br.gabrielsmartins.smartpayment.application.domain.state.RequestedOrderState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderTest {
	
	
	@Test
	@DisplayName("Given Order When Add Log Then Return Logs Size")
	public void givenOrderWhenAddLogThenReturnLogsSize() {
		Order order = new Order();
		Integer logsSize = order.addLog(LocalDateTime.now(), OrderStatus.REQUESTED);
		assertThat(logsSize).isEqualTo(1);
		assertThat(order.getLogs().size()).isEqualTo(1);
	}
	
	@Test
	@DisplayName("Given Order When Add Item Then Return Items Size")
	public void givenOrderWhenAddItemThenReturnItemsSize() {
		Order order = new Order();

		OrderItem item = new OrderItem();
		item.setProductId(UUID.randomUUID());
		item.setQuantity(10);
		item.setAmount(BigDecimal.TEN);

		Integer itemsSize = order.addItem(item);
		assertThat(itemsSize).isEqualTo(1);
		assertThat(order.getItems().size()).isEqualTo(1);
	}
	
	@Test
	@DisplayName("Given Order When Add Payment Method Then Return Items Size")
	public void givenOrderWhenAddPaymentMethodThenReturnItemsSize() {
		Order order = new Order();

		OrderItem item = new OrderItem();
		item.setProductId(UUID.randomUUID());
		item.setQuantity(10);
		item.setAmount(BigDecimal.TEN);

		Integer paymentMethosSize = order.addPaymentMethod(PaymentType.CASH, BigDecimal.TEN);
		assertThat(paymentMethosSize).isEqualTo(1);
		assertThat(order.getPaymentMethods().size()).isEqualTo(1);
	}

	
	@Test
	@DisplayName("Given Order When Change State Then Change Status")
	public void givenOrderWhenChangeStateThenChangeStatus() {
		Order order = new Order();
		order.next();
		assertThat(order.getStatus()).isEqualTo(OrderStatus.REQUESTED);
		assertThat(order.getState()).isInstanceOf(RequestedOrderState.class);
	}
}

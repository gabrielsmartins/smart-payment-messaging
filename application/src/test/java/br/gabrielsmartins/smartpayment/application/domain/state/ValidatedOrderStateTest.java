package br.gabrielsmartins.smartpayment.application.domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;

public class ValidatedOrderStateTest {
	
	private ValidatedOrderState state;
	private Order order;
	
	@BeforeEach
	public void setup() {
		this.order = new Order();
		this.state = new ValidatedOrderState(order);
	}
	
	@Test
	@DisplayName("Given State When Next Method Is Called Then Return Completed State")
	public void givenStateWhenNextMethodIsCalledThenReturnValidatedState() {
		OrderState completedState = this.state.next(order);
		assertThat(completedState).isInstanceOf(CompletedOrderState.class);
	}
	
	@Test
	@DisplayName("Given State When Get Status Method Is Called Then Return Order Status")
	public void givenStateWhenGetStatusMethodIsCalledThenReturnOrderStatus() {
		OrderStatus status = this.state.getStatus();
		assertThat(status).isEqualTo(OrderStatus.VALIDATED);
	}

}

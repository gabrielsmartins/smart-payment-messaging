package br.gabrielsmartins.smartpayment.application.domain.state;

import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NewOrderStateTest {
	
	private NewOrderState state;
	
	@BeforeEach
	public void setup() {
		this.state = new NewOrderState();
	}
	
	@Test
	@DisplayName("Given State When Next Method Is Called Then Return Requested State")
	public void givenStateWhenNextMethodIsCalledThenReturnCompletedState() {
		Order order = new Order();
		OrderState requestedState = this.state.next(order);
		assertThat(requestedState).isInstanceOf(RequestedOrderState.class);
	}
	
	@Test
	@DisplayName("Given State When Get Status Method Is Called Then Return Order Status")
	public void givenStateWhenGetStatusMethodIsCalledThenReturnOrderStatus() {
		OrderStatus status = this.state.getStatus();
		assertThat(status).isEqualTo(OrderStatus.NEW);
	}

}

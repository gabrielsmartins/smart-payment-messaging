package br.gabrielsmartins.smartpayment.application.domain.state;

import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import br.gabrielsmartins.smartpayment.application.exception.IllegalStateOrderException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NewOrderStateTest {
	
	private NewOrderState state;
	private Order order;
	
	@BeforeEach
	public void setup() {
		this.order = new Order();
		this.state = new NewOrderState();
	}

	@Test
	@DisplayName("Given State When Reject Method Is Called Then Throw Exception")
	public void givenStateWhenRejectMethodIsCalledThenThrowException() {
		assertThrows(IllegalStateOrderException.class, () -> this.state.reject(order));
	}

	@Test
	@DisplayName("Given State When Next Method Is Called Then Return Requested State")
	public void givenStateWhenNextMethodIsCalledThenReturnCompletedState() {
		OrderState state = this.state.next(order);
		assertThat(state).isInstanceOf(ReceivedOrderState.class);
	}
	
	@Test
	@DisplayName("Given State When Get Status Method Is Called Then Return Order Status")
	public void givenStateWhenGetStatusMethodIsCalledThenReturnOrderStatus() {
		OrderStatus status = this.state.getStatus();
		assertThat(status).isEqualTo(OrderStatus.NEW);
	}

}

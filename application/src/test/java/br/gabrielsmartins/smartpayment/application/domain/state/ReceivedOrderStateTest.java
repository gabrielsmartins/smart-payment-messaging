package br.gabrielsmartins.smartpayment.application.domain.state;

import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ReceivedOrderStateTest {
	
	private ReceivedOrderState state;
	private Order order;
	
	@BeforeEach
	public void setup() {
		this.order = new Order();
		this.state = new ReceivedOrderState(order);
	}

	@Test
	@DisplayName("Given State When Reject Method Is Called Then Return Rejected State")
	public void givenStateWhenRejectMethodIsCalledThenReturnRejectedState() {
		OrderState state = this.state.reject(order);
		assertThat(state).isInstanceOf(RejectedOrderState.class);
	}

	@Test
	@DisplayName("Given State When Next Method Is Called Then Return Validated State")
	public void givenStateWhenNextMethodIsCalledThenReturnValidatedState() {
		OrderState state = this.state.next(order);
		assertThat(state).isInstanceOf(ValidatedOrderState.class);
	}
	
	@Test
	@DisplayName("Given State When Get Status Method Is Called Then Return Order Status")
	public void givenStateWhenGetStatusMethodIsCalledThenReturnOrderStatus() {
		OrderStatus status = this.state.getStatus();
		assertThat(status).isEqualTo(OrderStatus.RECEIVED);
	}

}

package br.gabrielsmartins.smartpayment.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import br.gabrielsmartins.smartpayment.application.domain.state.RequestedOrderState;
import br.gabrielsmartins.smartpayment.application.ports.in.SendNotificationOrderUseCase;
import br.gabrielsmartins.smartpayment.application.ports.in.SubmitOrderUseCase.SubmitOrderCommand;

public class SubmitOrderServiceTest {
	
	private SubmitOrderService service;
	private SendNotificationOrderUseCase useCase;
	
	@BeforeEach
	public void setup() {
		this.useCase = mock(SendNotificationOrderUseCase.class);
		this.service = new SubmitOrderService(useCase);
	}
	
	@Test
	@DisplayName("Given Order When Submit Then Send Order")
	public void givenOrderWhenSubmitThenSendOrder() {
		Order order = new Order();
		SubmitOrderCommand command = new SubmitOrderCommand(order);
		
		this.service.submit(command);
		
		verify(useCase, times(1)).send(any(Order.class));
		assertThat(order.getStatus()).isEqualTo(OrderStatus.REQUESTED);
		assertThat(order.getState()).isInstanceOf(RequestedOrderState.class);
	}

}

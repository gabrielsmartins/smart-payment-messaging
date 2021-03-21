package br.gabrielsmartins.smartpayment.application.domain.state.observers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.state.RequestedOrderState;
import br.gabrielsmartins.smartpayment.application.ports.in.SendOrderStatusUseCase;

public class SendOrderStatusObserverTest {
	
	private SendOrderStatusObserver observer;
	private SendOrderStatusUseCase useCase;
	
	@BeforeEach
	public void setup() {
		this.useCase = mock(SendOrderStatusUseCase.class);
		this.observer = new SendOrderStatusObserver(useCase);
	}
	
	@Test
	@DisplayName("Given Observable When Update Then Send Order")
	public void givenObservableWhenUpdateThenSendOrder() {
		Order order = new Order();
		OrderStateObservable observable = new RequestedOrderState(order);
		this.observer.update(observable);
		
		verify(useCase, times(1)).send(any(Order.class));
	}

}

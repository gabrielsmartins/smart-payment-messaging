package br.gabrielsmartins.smartpayment.application.domain.state.observers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.state.ReceivedOrderState;
import br.gabrielsmartins.smartpayment.application.ports.in.SaveOrderUseCase;

public class SaveOrderObserverTest {
	
	private SaveOrderObserver observer;
	private SaveOrderUseCase useCase;
	
	@BeforeEach
	public void setup() {
		this.useCase = mock(SaveOrderUseCase.class);
		this.observer = new SaveOrderObserver(useCase);
	}
	
	@Test
	@DisplayName("Given Observable When Update Then Save Order")
	public void givenObservableWhenUpdateThenSaveOrder() {
		Order order = new Order();
		OrderStateObservable observable = new ReceivedOrderState(order);
		this.observer.update(observable);
		
		verify(useCase, times(1)).save(any(Order.class));
	}

}

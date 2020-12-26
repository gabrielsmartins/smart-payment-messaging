package br.gabrielsmartins.smartpayment.application.domain.state.observers;

import org.springframework.stereotype.Component;

import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.state.OrderState;
import br.gabrielsmartins.smartpayment.application.ports.in.SendOrderStatusUseCase;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SendOrderStatusObserver implements OrderStateObserver {
	
	private final SendOrderStatusUseCase useCase;
	
	@Override
	public void update(OrderStateObservable observable) {
		OrderState state = observable.getState();
		Order order = state.getOrder();
		this.useCase.send(order);
	}

}

package br.gabrielsmartins.smartpayment.application.domain.state.observers;

import org.springframework.stereotype.Component;

import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.state.OrderState;
import br.gabrielsmartins.smartpayment.application.ports.in.SaveOrderUseCase;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SaveOrderObserver implements OrderStateObserver {
	
	private final SaveOrderUseCase useCase;
	
	@Override
	public void update(OrderStateObservable observable) {
		OrderState state = observable.getState();
		Order order = state.getOrder();
		this.useCase.save(order);
	}

}

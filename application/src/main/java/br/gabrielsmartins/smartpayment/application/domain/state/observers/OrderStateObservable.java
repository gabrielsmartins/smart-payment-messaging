package br.gabrielsmartins.smartpayment.application.domain.state.observers;

import br.gabrielsmartins.smartpayment.application.domain.state.OrderState;

public interface OrderStateObservable {
	
	void register(OrderStateObserver observer);
	void remove(OrderStateObserver observer);
	void notifyAll(OrderState state);
	
	OrderState getState();
}

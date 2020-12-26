package br.gabrielsmartins.smartpayment.application.domain.state.observers;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;

public class OrderStateObserverFactory {

	private static final List<OrderStateObserver> OBSERVERS = new LinkedList<>();
	private static OrderStateObserverFactory instance;
	
	@Autowired
	private OrderStateObserverFactory(List<OrderStateObserver> observers) {
		observers.addAll(observers);
	}
	
	public static OrderStateObserverFactory getInstance() {
		if(Objects.isNull(instance)) {
			instance = new OrderStateObserverFactory(OBSERVERS);
		}
		return instance;
	}
	
	public List<OrderStateObserver> getObservers() {
		return OBSERVERS;
	}
	
	
}

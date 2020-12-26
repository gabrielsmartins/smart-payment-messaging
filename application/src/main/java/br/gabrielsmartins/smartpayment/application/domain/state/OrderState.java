package br.gabrielsmartins.smartpayment.application.domain.state;

import java.util.LinkedList;
import java.util.List;

import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import br.gabrielsmartins.smartpayment.application.domain.state.observers.OrderStateObservable;
import br.gabrielsmartins.smartpayment.application.domain.state.observers.OrderStateObserver;
import br.gabrielsmartins.smartpayment.application.domain.state.observers.OrderStateObserverFactory;
import lombok.Getter;

@Getter
public abstract class OrderState implements OrderStateObservable {

    protected Order order;
    protected List<OrderStateObserver> observers = new LinkedList<>();

    public OrderState(Order order){
        this.order = order;
        OrderStateObserverFactory factory = OrderStateObserverFactory.getInstance();
		List<OrderStateObserver> observers = factory.getObservers();
		observers.forEach(this::register);
    }

    public OrderState(){
        super();
    }

    public abstract OrderStatus getStatus();
    public abstract OrderState next(Order order);

    @Override
	public void register(OrderStateObserver observer) {
		this.observers.add(observer);
	}
    
    @Override
	public void remove(OrderStateObserver observer) {
		// TODO Auto-generated method stub
	}
    
    @Override
	public void notifyAll(OrderState state) {
		this.observers.forEach(o -> o.update(this));
	}
    
    @Override
    public OrderState getState() {
    	return this;
    }

}
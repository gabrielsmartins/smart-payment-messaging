package br.gabrielsmartins.smartpayment.application.domain.state;

import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import br.gabrielsmartins.smartpayment.application.domain.state.observers.OrderStateObservable;
import br.gabrielsmartins.smartpayment.application.domain.state.observers.OrderStateObserver;
import br.gabrielsmartins.smartpayment.application.domain.state.observers.OrderStateObserverFactory;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Getter
public abstract class OrderState implements OrderStateObservable {

    protected Order order;
    protected List<OrderStateObserver> observers = new LinkedList<>();

    public OrderState(Order order){
        this.order = order;
        this.order.setState(this);
        this.order.setStatus(this.getStatus());

        OrderLog log = new OrderLog();
        log.setStatus(this.order.getStatus());
        log.setDatetime(LocalDateTime.now());

        this.order.addLog(log);
        OrderStateObserverFactory factory = OrderStateObserverFactory.getInstance();
		List<OrderStateObserver> observers = factory.getObservers();
		observers.forEach(this::register);
		this.notifyAll(this);
    }

    public OrderState(){
        super();
    }

    public abstract OrderStatus getStatus();

    public abstract OrderState reject(Order order);

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

package br.gabrielsmartins.smartpayment.application.domain.state;

import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import br.gabrielsmartins.smartpayment.application.exception.IllegalStateOrderException;

public class NewOrderState extends OrderState {

    public NewOrderState(){
        super();
    }
    
    @Override
    public OrderStatus getStatus() {
        return OrderStatus.NEW;
    }

    @Override
    public OrderState reject(Order order) {
        throw new IllegalStateOrderException("Isn't possible to reject an order already in New State");
    }

    @Override
    public OrderState next(Order order) {
        return new ReceivedOrderState(order);
    }
    
    
    

}

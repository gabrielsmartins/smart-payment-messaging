package br.gabrielsmartins.smartpayment.application.domain.state;

import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;

public class NewOrderState extends OrderState {

    public NewOrderState(){
        super();
    }
    
    @Override
    public OrderStatus getStatus() {
        return OrderStatus.NEW;
    }

    @Override
    public OrderState next(Order order) {
        return new RequestedOrderState(order);
    }
    
    
    

}

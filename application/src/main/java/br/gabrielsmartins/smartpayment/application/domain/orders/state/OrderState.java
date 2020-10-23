package br.gabrielsmartins.smartpayment.application.domain.orders.state;

import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import br.gabrielsmartins.smartpayment.application.domain.orders.Order;
import lombok.Getter;

@Getter
public abstract class OrderState {

    protected Order order;

    public OrderState(Order order){
        this.order = order;
    }

    public OrderState(){
        super();
    }

    public abstract OrderStatus getStatus();
    public abstract OrderState next();


}

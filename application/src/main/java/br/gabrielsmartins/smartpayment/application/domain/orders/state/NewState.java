package br.gabrielsmartins.smartpayment.application.domain.orders.state;

import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;

public class NewState extends OrderState {

    public NewState(){
        super();
    }
    @Override
    public OrderStatus getStatus() {
        return OrderStatus.NEW;
    }

    @Override
    public OrderState next() {
        return new ReceivedState(this.order);
    }
}

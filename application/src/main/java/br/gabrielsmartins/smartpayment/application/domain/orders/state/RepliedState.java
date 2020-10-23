package br.gabrielsmartins.smartpayment.application.domain.orders.state;

import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;

public class RepliedState extends OrderState{

    @Override
    public OrderStatus getStatus() {
        return OrderStatus.REPLIED;
    }

    @Override
    public OrderState next() {
        return new FinishedState(this.order);
    }

}

package br.gabrielsmartins.smartpayment.application.domain.orders.state;

import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import br.gabrielsmartins.smartpayment.application.domain.orders.Order;

public class ReceivedState extends OrderState{

    public ReceivedState(Order order) {
        super(order);
    }

    @Override
    public OrderStatus getStatus() {
        return OrderStatus.RECEIVED;
    }

    @Override
    public OrderState next() {
        return new StartedState(this.order);
    }
}

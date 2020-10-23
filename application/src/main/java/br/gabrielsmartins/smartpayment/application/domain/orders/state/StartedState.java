package br.gabrielsmartins.smartpayment.application.domain.orders.state;

import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import br.gabrielsmartins.smartpayment.application.domain.orders.Order;

public class StartedState extends OrderState {

    public StartedState(Order order) {
        super(order);
    }

    @Override
    public Order getOrder() {
        return this.order;
    }

    @Override
    public OrderStatus getStatus() {
        return OrderStatus.STARTED;
    }

    @Override
    public OrderState next() {
        return new FinishedState(order);
    }
}

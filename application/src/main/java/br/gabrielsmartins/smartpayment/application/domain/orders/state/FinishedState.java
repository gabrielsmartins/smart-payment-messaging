package br.gabrielsmartins.smartpayment.application.domain.orders.state;

import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import br.gabrielsmartins.smartpayment.application.domain.orders.Order;
import br.gabrielsmartins.smartpayment.application.exception.IllegalStateOrderException;

public class FinishedState extends OrderState {

    public FinishedState(Order order) {
        super(order);
    }

    @Override
    public OrderStatus getStatus() {
        return OrderStatus.FINISHED;
    }

    @Override
    public OrderState next() {
        throw new IllegalStateOrderException("Order is already finished");
    }
}

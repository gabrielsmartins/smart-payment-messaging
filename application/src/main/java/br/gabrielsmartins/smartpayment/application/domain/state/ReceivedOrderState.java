package br.gabrielsmartins.smartpayment.application.domain.state;

import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;

public class ReceivedOrderState extends OrderState{

    public ReceivedOrderState(Order order) {
        super(order);
    }

    @Override
    public OrderStatus getStatus() {
        return OrderStatus.RECEIVED;
    }

    @Override
    public OrderState reject(Order order) {
        return new RejectedOrderState(order);
    }

    @Override
    public OrderState next(Order order) {
        return new ValidatedOrderState(order);
    }

}

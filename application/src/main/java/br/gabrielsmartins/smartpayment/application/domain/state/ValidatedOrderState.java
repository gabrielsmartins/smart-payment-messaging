package br.gabrielsmartins.smartpayment.application.domain.state;

import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;

public class ValidatedOrderState extends OrderState {

    public ValidatedOrderState(Order order) {
        super(order);
    }

    @Override
    public Order getOrder() {
        return this.order;
    }

    @Override
    public OrderStatus getStatus() {
        return OrderStatus.VALIDATED;
    }

    @Override
    public OrderState reject(Order order) {
        return new RejectedOrderState(order);
    }

    @Override
    public OrderState next(Order order) {
        return new CompletedOrderState(order);
    }


}

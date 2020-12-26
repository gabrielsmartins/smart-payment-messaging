package br.gabrielsmartins.smartpayment.application.domain.state;

import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;

public class RequestedOrderState extends OrderState{

    public RequestedOrderState(Order order) {
        super(order);
    }

    @Override
    public OrderStatus getStatus() {
        return OrderStatus.REQUESTED;
    }

    @Override
    public OrderState next(Order order) {
        return new ValidatedOrderState(this.order);
    }

}

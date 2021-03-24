package br.gabrielsmartins.smartpayment.application.domain.state;

import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import br.gabrielsmartins.smartpayment.application.exception.IllegalStateOrderException;

public class RejectedOrderState extends OrderState {

    public RejectedOrderState(Order order) {
        super(order);
    }

    @Override
    public Order getOrder() {
        return this.order;
    }

    @Override
    public OrderStatus getStatus() {
        return OrderStatus.REJECTED;
    }

    @Override
    public OrderState reject(Order order) {
        throw new IllegalStateOrderException("Isn't possible to reject an order already rejected");
    }

    @Override
    public OrderState next(Order order) {
        throw new IllegalStateOrderException("Order is already rejected");
    }


}

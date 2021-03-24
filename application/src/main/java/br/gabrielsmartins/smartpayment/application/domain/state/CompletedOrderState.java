package br.gabrielsmartins.smartpayment.application.domain.state;

import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import br.gabrielsmartins.smartpayment.application.exception.IllegalStateOrderException;

public class CompletedOrderState extends OrderState {

    public CompletedOrderState(Order order) {
        super(order);
    }

    @Override
    public OrderStatus getStatus() {
        return OrderStatus.COMPLETED;
    }

    @Override
    public OrderState reject(Order order) {
        throw new IllegalStateOrderException("Isn't possible to reject an order already completed");
    }

    @Override
    public OrderState next(Order order) {
        throw new IllegalStateOrderException("Order is already finished");
    }

	
}

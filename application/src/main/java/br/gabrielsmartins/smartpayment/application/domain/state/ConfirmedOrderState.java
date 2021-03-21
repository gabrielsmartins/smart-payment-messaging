package br.gabrielsmartins.smartpayment.application.domain.state;

import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;

public class ConfirmedOrderState extends OrderState{

    public ConfirmedOrderState(Order order) {
		super(order);
	}

	@Override
    public OrderStatus getStatus() {
        return OrderStatus.CONFIRMED;
    }

    @Override
    public OrderState next(Order order) {
        return new CompletedOrderState(order);
    }


}

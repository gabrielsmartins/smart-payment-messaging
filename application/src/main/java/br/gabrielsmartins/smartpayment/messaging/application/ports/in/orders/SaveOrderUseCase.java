package br.gabrielsmartins.smartpayment.messaging.application.ports.in.orders;

import br.gabrielsmartins.smartpayment.messaging.application.domain.orders.Order;

public interface SaveOrderUseCase {

	Order save(Order order);
}

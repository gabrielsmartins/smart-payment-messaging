package br.gabrielsmartins.smartpayment.application.ports.in.orders;

import br.gabrielsmartins.smartpayment.application.domain.orders.Order;

public interface SaveOrderUseCase {

	Order save(Order order);
}

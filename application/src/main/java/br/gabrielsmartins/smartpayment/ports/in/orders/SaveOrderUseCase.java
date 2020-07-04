package br.gabrielsmartins.smartpayment.ports.in.orders;

import br.gabrielsmartins.smartpayment.domain.orders.Order;

public interface SaveOrderUseCase {

	Order save(Order order);
}

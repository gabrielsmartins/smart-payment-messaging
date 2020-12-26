package br.gabrielsmartins.smartpayment.application.ports.in;

import br.gabrielsmartins.smartpayment.application.domain.Order;

public interface SaveOrderUseCase {

	Order save(Order order);
}

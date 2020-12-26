package br.gabrielsmartins.smartpayment.application.ports.in;

import br.gabrielsmartins.smartpayment.application.domain.Order;

public interface SendOrderUseCase {

	void send(Order order);
	
}

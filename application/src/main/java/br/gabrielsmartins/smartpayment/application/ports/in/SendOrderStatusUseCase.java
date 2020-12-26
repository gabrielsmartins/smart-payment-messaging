package br.gabrielsmartins.smartpayment.application.ports.in;

import br.gabrielsmartins.smartpayment.application.domain.Order;

public interface SendOrderStatusUseCase {

	void send(Order order);
	
}

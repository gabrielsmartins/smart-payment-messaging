package br.gabrielsmartins.smartpayment.application.ports.out;

import br.gabrielsmartins.smartpayment.application.domain.Order;

public interface SendOrderStatusPort {

	void send(Order order);
	
}

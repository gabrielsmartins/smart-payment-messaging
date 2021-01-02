package br.gabrielsmartins.smartpayment.application.ports.in;

import br.gabrielsmartins.smartpayment.application.domain.Order;

public interface SendNotificationOrderUseCase {

	void send(Order order);
	
}

package br.gabrielsmartins.smartpayment.application.ports.out;

import br.gabrielsmartins.smartpayment.application.domain.Order;

public interface SendNotificationOrderPort {

	void send(Order order);
	
}

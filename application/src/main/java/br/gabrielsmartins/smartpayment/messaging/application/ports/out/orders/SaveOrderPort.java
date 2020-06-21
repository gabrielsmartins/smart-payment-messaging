package br.gabrielsmartins.smartpayment.messaging.application.ports.out.orders;

import br.gabrielsmartins.smartpayment.messaging.application.domain.orders.Order;

public interface SaveOrderPort {
	Order save(Order order);
}

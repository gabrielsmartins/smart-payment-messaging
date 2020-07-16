package br.gabrielsmartins.smartpayment.application.service;

import br.gabrielsmartins.smartpayment.application.ports.in.orders.SaveOrderUseCase;
import br.gabrielsmartins.smartpayment.application.domain.orders.Order;
import br.gabrielsmartins.smartpayment.application.ports.out.orders.SaveOrderPort;
import br.gabrielsmartins.smartpayment.common.stereotype.UseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class SaveOrderService implements SaveOrderUseCase {

	private final SaveOrderPort port;
	
	@Override
	public Order save(Order order) {
		return port.save(order);
	}

}

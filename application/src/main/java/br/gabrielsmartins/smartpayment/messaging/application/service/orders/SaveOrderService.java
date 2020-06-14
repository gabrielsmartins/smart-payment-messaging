package br.gabrielsmartins.smartpayment.messaging.application.service.orders;

import br.gabrielsmartins.smartpayment.messaging.application.domain.orders.Order;
import br.gabrielsmartins.smartpayment.messaging.application.ports.in.orders.SaveOrderUseCase;
import br.gabrielsmartins.smartpayment.messaging.application.ports.out.orders.SaveOrderPort;
import br.gabrielsmartins.smartpayment.messaging.common.stereotype.UseCase;
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

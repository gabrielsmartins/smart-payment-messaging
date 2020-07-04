package br.gabrielsmartins.smartpayment.service.orders;

import br.gabrielsmartins.smartpayment.ports.in.orders.SaveOrderUseCase;
import br.gabrielsmartins.smartpayment.domain.orders.Order;
import br.gabrielsmartins.smartpayment.ports.out.orders.SaveOrderPort;
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

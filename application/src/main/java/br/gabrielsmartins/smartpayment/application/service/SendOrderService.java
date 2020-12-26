package br.gabrielsmartins.smartpayment.application.service;

import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.ports.in.SendOrderUseCase;
import br.gabrielsmartins.smartpayment.application.ports.out.SendOrderPort;
import br.gabrielsmartins.smartpayment.common.stereotype.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class SendOrderService implements SendOrderUseCase {
	
	private final SendOrderPort port;

	@Override
	public void send(Order order) {
		this.port.send(order);
	}

}

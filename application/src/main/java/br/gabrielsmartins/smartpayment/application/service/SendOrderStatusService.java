package br.gabrielsmartins.smartpayment.application.service;

import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.ports.in.SendOrderStatusUseCase;
import br.gabrielsmartins.smartpayment.application.ports.out.SendOrderStatusPort;
import br.gabrielsmartins.smartpayment.common.stereotype.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class SendOrderStatusService implements SendOrderStatusUseCase{
	
	private final SendOrderStatusPort port;

	@Override
	public void send(Order order) {
		this.port.send(order);
	}
	
}

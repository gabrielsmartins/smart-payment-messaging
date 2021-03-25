package br.gabrielsmartins.smartpayment.application.service;

import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.ports.in.SendOrderStatusUseCase;
import br.gabrielsmartins.smartpayment.application.ports.out.SendOrderStatusPort;
import br.gabrielsmartins.smartpayment.common.stereotype.UseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@UseCase
@RequiredArgsConstructor
public class SendOrderStatusService implements SendOrderStatusUseCase{
	
	private final SendOrderStatusPort port;

	@Override
	public Mono<Void> send(Order order) {
		return this.port.send(order);
	}
	
}

package br.gabrielsmartins.smartpayment.application.service;

import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.ports.in.SendNotificationOrderUseCase;
import br.gabrielsmartins.smartpayment.application.ports.out.SendNotificationOrderPort;
import br.gabrielsmartins.smartpayment.common.stereotype.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class SendNotificationOrderService implements SendNotificationOrderUseCase {
	
	private final SendNotificationOrderPort port;

	@Override
	public Mono<Void> send(Order order) {
		return this.port.send(order);
	}

}

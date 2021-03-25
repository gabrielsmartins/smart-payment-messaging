package br.gabrielsmartins.smartpayment.application.service;

import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.ports.in.SendNotificationOrderUseCase;
import br.gabrielsmartins.smartpayment.application.ports.in.SubmitOrderUseCase;
import br.gabrielsmartins.smartpayment.common.stereotype.UseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@UseCase
@RequiredArgsConstructor
public class SubmitOrderService implements SubmitOrderUseCase {
	
	private final SendNotificationOrderUseCase useCase;

	@Override
	public Mono<Order> submit(SubmitOrderCommand command) {
		Order order = command.getOrder();
		this.useCase.send(order);
		order.setCreatedAt(LocalDateTime.now());
		order.next();
		return Mono.just(order);
	}

}

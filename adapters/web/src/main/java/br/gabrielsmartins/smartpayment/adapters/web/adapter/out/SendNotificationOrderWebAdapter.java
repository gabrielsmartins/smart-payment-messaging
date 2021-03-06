package br.gabrielsmartins.smartpayment.adapters.web.adapter.out;

import org.springframework.stereotype.Component;

import br.gabrielsmartins.smartpayment.adapters.web.adapter.out.dto.NotificationOrderDTO;
import br.gabrielsmartins.smartpayment.adapters.web.mapper.out.NotificationOrderWebMapper;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.ports.out.SendNotificationOrderPort;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SendNotificationOrderWebAdapter implements SendNotificationOrderPort {

	private final NotificationOrderWebClient client;
	private final NotificationOrderWebMapper mapper;

	@Override
	public Mono<Void> send(Order order) {
		NotificationOrderDTO requestDTO = mapper.mapToDto(order);
		this.client.submit(requestDTO);
		return Mono.empty().then();
	}

}

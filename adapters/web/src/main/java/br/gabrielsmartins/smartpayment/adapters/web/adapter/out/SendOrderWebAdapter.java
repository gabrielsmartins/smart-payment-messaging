package br.gabrielsmartins.smartpayment.adapters.web.adapter.out;

import org.springframework.stereotype.Component;

import br.gabrielsmartins.smartpayment.adapters.web.adapter.out.dto.OrderRequestDTO;
import br.gabrielsmartins.smartpayment.adapters.web.mapper.out.OrderRequestMapper;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.ports.out.SendOrderPort;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SendOrderWebAdapter implements SendOrderPort {

	private final SendOrderWebClient client;
	private final OrderRequestMapper mapper;

	@Override
	public void send(Order order) {
		OrderRequestDTO requestDTO = mapper.mapToDto(order);
		this.client.submit(requestDTO);
	}

}

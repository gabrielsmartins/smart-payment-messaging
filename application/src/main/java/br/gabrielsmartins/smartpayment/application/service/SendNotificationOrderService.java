package br.gabrielsmartins.smartpayment.application.service;

import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.ports.in.SendNotificationOrderUseCase;
import br.gabrielsmartins.smartpayment.application.ports.out.SendNotificationOrderPort;
import br.gabrielsmartins.smartpayment.common.stereotype.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class SendNotificationOrderService implements SendNotificationOrderUseCase {
	
	private final SendNotificationOrderPort port;

	@Override
	public void send(Order order) {
		try{
			this.port.send(order);
		}catch (Exception e){
	        log.error("Error sending order: ", e);
		}
	}

}

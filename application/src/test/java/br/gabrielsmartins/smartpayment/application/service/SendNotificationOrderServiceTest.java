package br.gabrielsmartins.smartpayment.application.service;

import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.ports.out.SendNotificationOrderPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SendNotificationOrderServiceTest {

	private SendNotificationOrderService service;
	private SendNotificationOrderPort port;
	
	@BeforeEach
	public void setup() {
		this.port = mock(SendNotificationOrderPort.class);
		this.service = new SendNotificationOrderService(port);
	}
	
	
	@Test
	@DisplayName("Given Order When Send Then Return Sent Order")
	public void givenOrderWhenSendThenReturnSentOrder() {
		this.service.send(new Order());
		verify(this.port, times(1)).send(any(Order.class));
	}
	
}

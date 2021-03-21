package br.gabrielsmartins.smartpayment.application.service;



import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.ports.out.SendOrderStatusPort;

public class SendOrderStatusServiceTest {
	
	private SendOrderStatusService service;
	private SendOrderStatusPort port; 
	
	@BeforeEach
	public void setup() {
		this.port = mock(SendOrderStatusPort.class);
		this.service = new SendOrderStatusService(port);
	}
	
	@Test
	@DisplayName("Given Order When Send Then Call Port")
	public void givenOrderWhenSendThenCallPort() {
		this.service.send(new Order());
		verify(this.port, times(1)).send(any(Order.class));
	}

}

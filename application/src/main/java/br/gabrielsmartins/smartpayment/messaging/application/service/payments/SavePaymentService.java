package br.gabrielsmartins.smartpayment.messaging.application.service.payments;

import br.gabrielsmartins.smartpayment.messaging.application.domain.payments.Payment;
import br.gabrielsmartins.smartpayment.messaging.application.ports.in.payments.SavePaymentUseCase;
import br.gabrielsmartins.smartpayment.messaging.application.ports.out.payments.SavePaymentPort;
import br.gabrielsmartins.smartpayment.messaging.common.stereotype.UseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class SavePaymentService implements SavePaymentUseCase{
	
	private final SavePaymentPort port;

	@Override
	public Payment save(Payment payment) {
		return port.save(payment);
	}

}

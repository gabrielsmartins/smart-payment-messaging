package br.gabrielsmartins.smartpayment.messaging.application.ports.in.payments;

import br.gabrielsmartins.smartpayment.messaging.application.domain.payments.Payment;

public interface SavePaymentUseCase {
	
	Payment save(Payment payment);

}

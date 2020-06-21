package br.gabrielsmartins.smartpayment.messaging.application.ports.out.payments;

import br.gabrielsmartins.smartpayment.messaging.application.domain.payments.Payment;

public interface SavePaymentPort {
	Payment save(Payment payment);
}

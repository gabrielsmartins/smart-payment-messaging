package br.gabrielsmartins.smartpayment.messaging.application.domain.payments.strategy;

import java.math.BigDecimal;

public interface PaymentStrategy {
	
	boolean pay(BigDecimal amount);

}

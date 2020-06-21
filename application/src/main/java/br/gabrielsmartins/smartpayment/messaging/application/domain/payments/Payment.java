package br.gabrielsmartins.smartpayment.messaging.application.domain.payments;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.gabrielsmartins.smartpayment.messaging.application.domain.enums.PaymentType;
import br.gabrielsmartins.smartpayment.messaging.application.domain.payments.strategy.PaymentStrategy;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class Payment {
	
	@Getter
	private final String id;
	
	@Getter @Setter
	private String paymentNumberIdentifier;
	
	@Getter @Setter
	private LocalDate dueDate;
	
	@Getter @Setter
	private LocalDate paymentDate;
	
	@Getter @Setter
	private BigDecimal amount;
	
	@Getter @Setter
	private BigDecimal discount;
	
	@Getter @Setter
	private BigDecimal amountPaid;
	
	@Getter @Setter
	private PaymentType paymentType; 
	
	@Getter @Setter
	private PaymentStrategy paymentStrategy;

}

package br.gabrielsmartins.smartpayment.messaging.application.domain.orders;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.gabrielsmartins.smartpayment.messaging.application.domain.enums.PaymentType;
import lombok.*;


@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Order {

	@Setter(AccessLevel.NONE)
	private final String id;
	private String paymentNumberIdentifier;
	private LocalDate dueDate;
	private LocalDate paymentDate;
	private BigDecimal totalAmount;
	private BigDecimal discount;
	private BigDecimal totalAmountPaid;
	private PaymentType paymentType;

}

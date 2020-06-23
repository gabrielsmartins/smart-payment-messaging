package br.gabrielsmartins.smartpayment.messaging.application.domain.payments;

import br.gabrielsmartins.smartpayment.messaging.application.domain.enums.PaymentType;
import br.gabrielsmartins.smartpayment.messaging.application.domain.orders.Order;
import br.gabrielsmartins.smartpayment.messaging.application.domain.payments.strategy.PaymentStrategy;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString(exclude = {"paymentMethods"})
@Builder(setterPrefix = "with")
public class Payment {

	private String id;
	private Order order;
	private String paymentNumberIdentifier;
	private LocalDate dueDate;
	private LocalDate paymentDate;
	private BigDecimal totalAmount;
	private BigDecimal totalAmountPaid;
	private final List<PaymentMethod> paymentMethods = new LinkedList<>();

	public Integer addPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethods.add(paymentMethod);
		return this.paymentMethods.size();
	}


	@NoArgsConstructor
	@AllArgsConstructor
	@Getter @Setter
	@ToString
	@Builder(setterPrefix = "with")
	public static class PaymentMethod {

		private Long id;
		private Payment payment;
		private BigDecimal discount;
		private BigDecimal totalAmountPaid;
		private PaymentType paymentType;
		private PaymentStrategy paymentStrategy;

	}

}

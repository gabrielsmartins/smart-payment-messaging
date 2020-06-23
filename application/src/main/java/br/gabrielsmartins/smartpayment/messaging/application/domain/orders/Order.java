package br.gabrielsmartins.smartpayment.messaging.application.domain.orders;

import br.gabrielsmartins.smartpayment.messaging.application.domain.enums.PaymentType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;


@Getter
@Setter
@ToString(exclude = {"paymentMethods"})
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class Order {

	private String id;
	private String paymentNumberIdentifier;
	private LocalDate dueDate;
	private LocalDate paymentDate;
	private BigDecimal totalAmount;
	private BigDecimal totalAmountPaid;
	private final List<OrderPaymentMethod> paymentMethods = new LinkedList<>();

	public Integer addPaymentMethod(OrderPaymentMethod paymentMethod) {
		this.paymentMethods.add(paymentMethod);
		return this.paymentMethods.size();
	}

	@Getter
	@Setter
	@ToString
	@Builder(setterPrefix = "with")
	@NoArgsConstructor
	@AllArgsConstructor
	public static class OrderPaymentMethod {

		private Long id;
		private Order order;
		private BigDecimal discount;
		private BigDecimal totalAmountPaid;
		private PaymentType paymentType;
	}

}

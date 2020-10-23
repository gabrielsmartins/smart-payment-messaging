package br.gabrielsmartins.smartpayment.application.domain.orders;

import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import br.gabrielsmartins.smartpayment.application.domain.enums.PaymentType;
import br.gabrielsmartins.smartpayment.application.domain.orders.state.NewState;
import br.gabrielsmartins.smartpayment.application.domain.orders.state.OrderState;
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
	private OrderStatus status = OrderStatus.NEW;
	private OrderState state = new NewState();
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

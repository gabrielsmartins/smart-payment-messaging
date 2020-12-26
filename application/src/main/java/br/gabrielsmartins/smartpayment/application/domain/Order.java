package br.gabrielsmartins.smartpayment.application.domain;

import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import br.gabrielsmartins.smartpayment.application.domain.enums.PaymentType;
import br.gabrielsmartins.smartpayment.application.domain.state.NewOrderState;
import br.gabrielsmartins.smartpayment.application.domain.state.OrderState;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;


@Getter
@Setter
@ToString
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class Order {

	private UUID id;
	private UUID customerId;
	private LocalDateTime createdAt;
	private LocalDateTime finishedAt;
	private BigDecimal totalAmount;
	private BigDecimal totalDiscount;
	
	@Builder.Default
	private OrderStatus status = OrderStatus.NEW;
	
	@Builder.Default
	private OrderState state = new NewOrderState();
		
	private final Map<LocalDateTime, OrderStatus> logs = new LinkedHashMap<>();
	private final List<OrderItem> items = new LinkedList<>();
	private final Map<PaymentType, BigDecimal> paymentMethods = new LinkedHashMap<>();

	public Integer addLog(LocalDateTime datetime, OrderStatus status) {
		this.logs.put(datetime, status);
		return this.logs.size();
	}
	
	public Integer addItem(OrderItem item) {
		item.setOrder(this);
		this.items.add(item);
		return this.items.size();
	}
	
	public Integer addPaymentMethod(PaymentType paymentType, BigDecimal amount) {
		this.paymentMethods.put(paymentType, amount);
		return this.paymentMethods.size();
	}

	public OrderState next() {
		 this.state = this.state.next(this);
		 this.status = state.getStatus();
		 return this.state;
	}

	@Getter
	@Setter
	@ToString
	@Builder(setterPrefix = "with")
	@NoArgsConstructor
	@AllArgsConstructor
	public static class OrderItem {

		private Order order;
		private UUID productId;
		private Integer quantity;
		private BigDecimal amount;

	}

	


}

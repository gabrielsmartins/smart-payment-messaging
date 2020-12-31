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
@ToString(exclude = {"logs", "items", "paymentMethods"})
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class Order {

	private String id;
	private UUID customerId;
	private LocalDateTime createdAt;
	private LocalDateTime finishedAt;
	private BigDecimal totalAmount;
	private BigDecimal totalDiscount;
	
	@Builder.Default
	private OrderStatus status = OrderStatus.NEW;
	
	@Builder.Default
	private OrderState state = new NewOrderState();

	@Getter(AccessLevel.NONE)
	private final Map<LocalDateTime, OrderStatus> logs = new HashMap<>();

	@Getter(AccessLevel.NONE)
	private final List<OrderItem> items = new LinkedList<>();

	@Getter(AccessLevel.NONE)
	private final Map<PaymentType, BigDecimal> paymentMethods = new HashMap<>();

	public Map<LocalDateTime, OrderStatus> getLogs() {
		return Collections.unmodifiableMap(logs);
	}

	public List<OrderItem> getItems() {
		return Collections.unmodifiableList(items);
	}

	public Map<PaymentType, BigDecimal> getPaymentMethods() {
		return Collections.unmodifiableMap(paymentMethods);
	}

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
	@ToString(exclude = {"order"})
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

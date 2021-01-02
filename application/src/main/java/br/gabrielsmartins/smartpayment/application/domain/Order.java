package br.gabrielsmartins.smartpayment.application.domain;

import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import br.gabrielsmartins.smartpayment.application.domain.state.NewOrderState;
import br.gabrielsmartins.smartpayment.application.domain.state.OrderLog;
import br.gabrielsmartins.smartpayment.application.domain.state.OrderState;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;


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
	private final List<OrderLog> logs = new LinkedList<>();

	@Getter(AccessLevel.NONE)
	private final List<OrderItem> items = new LinkedList<>();

	@Getter(AccessLevel.NONE)
	private final List<PaymentMethod> paymentMethods = new LinkedList<>();

	public List<OrderLog> getLogs() {
		return Collections.unmodifiableList(logs);
	}

	public List<OrderItem> getItems() {
		return Collections.unmodifiableList(items);
	}

	public List<PaymentMethod> getPaymentMethods() {
		return Collections.unmodifiableList(paymentMethods);
	}

	public Integer addLog(OrderLog log) {
		log.setOrder(this);
		this.logs.add(log);
		return this.logs.size();
	}
	
	public Integer addItem(OrderItem item) {
		item.setOrder(this);
		this.items.add(item);
		return this.items.size();
	}
	
	public Integer addPaymentMethod(PaymentMethod paymentMethod) {
		paymentMethod.setOrder(this);
		this.paymentMethods.add(paymentMethod);
		return this.paymentMethods.size();
	}

	public OrderState next() {
		 return this.state.next(this);
	}





}

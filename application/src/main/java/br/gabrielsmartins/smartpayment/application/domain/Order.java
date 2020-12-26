package br.gabrielsmartins.smartpayment.application.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import br.gabrielsmartins.smartpayment.application.domain.enums.PaymentType;
import br.gabrielsmartins.smartpayment.application.domain.state.NewOrderState;
import br.gabrielsmartins.smartpayment.application.domain.state.OrderState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class Order {

	private String id;
	private String customerId;
	private LocalDateTime createdAt;
	private LocalDateTime finishedAt;
	private BigDecimal totalAmount;
	private BigDecimal totalDiscount;
	
	@Builder.Default
	private OrderStatus status = OrderStatus.NEW;
	
	@Builder.Default
	private OrderState state = new NewOrderState();
		
	private final Map<LocalDateTime, OrderStatus> logs = new LinkedHashMap<>();
	private final Map<String, BigDecimal> items = new LinkedHashMap<>();
	private final Map<PaymentType, BigDecimal> paymentMethods = new LinkedHashMap<>();

	public Integer addLog(LocalDateTime datetime, OrderStatus status) {
		this.logs.put(datetime, status);
		return this.logs.size();
	}
	
	public Integer addItem(String productId, BigDecimal amount) {
		this.items.put(productId, amount);
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
	


}

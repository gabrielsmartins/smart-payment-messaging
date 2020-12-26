package br.gabrielsmartins.smartpayment.adapters.persistence.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import br.gabrielsmartins.smartpayment.application.domain.enums.PaymentType;
import br.gabrielsmartins.smartpayment.application.domain.state.OrderState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@ToString
@Getter
@Setter
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
@Document("orders")
public class OrderEntity {

    @Id
    @Field("order_id")
    private String id;

    @Field("customer_id")
    private String customerId;

    @Field("created_at")
    private LocalDateTime createdAt;

    @Field("finished_at")
    private LocalDateTime finishedAt;

    @Field("total_amount")
    private BigDecimal totalAmount;

    @Field("total_discount")
    private BigDecimal totalDiscount;
    
    @Field("status")
	private OrderStatus status;
	
	@Transient
	private OrderState state;

    @Field("logs")
    private final Map<LocalDateTime, OrderStatus> logs = new LinkedHashMap<>();
    
    @Field("items")
	private final Map<String, BigDecimal> items = new LinkedHashMap<>();
	
    @Field("payment_methods")
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


}

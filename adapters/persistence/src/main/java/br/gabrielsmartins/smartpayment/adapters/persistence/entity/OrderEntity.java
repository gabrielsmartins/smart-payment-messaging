package br.gabrielsmartins.smartpayment.adapters.persistence.entity;

import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import br.gabrielsmartins.smartpayment.application.domain.enums.PaymentType;
import br.gabrielsmartins.smartpayment.application.domain.state.OrderState;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;


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
    private UUID id;

    @Field("customer_id")
    private UUID customerId;

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
	private final List<OrderItemEntity> items = new LinkedList<>();
	
    @Field("payment_methods")
	private final Map<PaymentType, BigDecimal> paymentMethods = new LinkedHashMap<>();

	public Integer addLog(LocalDateTime datetime, OrderStatus status) {
		this.logs.put(datetime, status);
		return this.logs.size();
	}
	
	public Integer addItem(OrderItemEntity item) {
		item.setOrder(this);
	    this.items.add(item);
		return this.items.size();
	}
	
	public Integer addPaymentMethod(PaymentType paymentType, BigDecimal amount) {
		this.paymentMethods.put(paymentType, amount);
		return this.paymentMethods.size();
	}

    @Getter
    @Setter
    @ToString
    @Builder(setterPrefix = "with")
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderItemEntity {

	    @Transient
	    private OrderEntity order;

        @Field("product_id")
        private UUID productId;

        @Field("quantity")
        private Integer quantity;

        @Field("amount")
        private BigDecimal amount;

    }

}

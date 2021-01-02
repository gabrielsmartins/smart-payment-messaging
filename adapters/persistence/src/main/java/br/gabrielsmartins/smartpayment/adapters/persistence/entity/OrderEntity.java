package br.gabrielsmartins.smartpayment.adapters.persistence.entity;

import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import br.gabrielsmartins.smartpayment.application.domain.state.OrderState;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;


@ToString(exclude = {"logs", "items", "paymentMethods"})
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
    @Getter(AccessLevel.NONE)
    private final List<OrderLogEntity> logs = new LinkedList<>();
    
    @Field("items")
    @Getter(AccessLevel.NONE)
	private final List<OrderItemEntity> items = new LinkedList<>();
	
    @Field("payment_methods")
    @Getter(AccessLevel.NONE)
	private final List<PaymentMethodEntity> paymentMethods = new LinkedList<>();

    public List<OrderLogEntity> getLogs() {
        return Collections.unmodifiableList(logs);
    }

    public List<OrderItemEntity> getItems() {
        return Collections.unmodifiableList(items);
    }

    public List<PaymentMethodEntity> getPaymentMethods() {
        return Collections.unmodifiableList(paymentMethods);
    }

	public Integer addLog(OrderLogEntity log) {
        log.setOrder(this);
		this.logs.add(log);
		return this.logs.size();
	}
	
	public Integer addItem(OrderItemEntity item) {
		item.setOrder(this);
	    this.items.add(item);
		return this.items.size();
	}
	
	public Integer addPaymentMethod(PaymentMethodEntity paymentMethod) {
        paymentMethod.setOrder(this);
		this.paymentMethods.add(paymentMethod);
		return this.paymentMethods.size();
	}


}

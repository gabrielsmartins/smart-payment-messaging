package br.gabrielsmartins.smartpayment.adapters.web.adapter.in.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class OrderDTO {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private String id;

    @JsonProperty("customer_id")
    private UUID customerId;

    @JsonProperty(value = "created_at", access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime createdAt;

    @JsonProperty(value = "finished_at", access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime finishedAt;

    @JsonProperty("total_amount")
    private BigDecimal totalAmount;

    @JsonProperty("total_discount")
    private BigDecimal totalDiscount;
    
    @JsonProperty(value = "status", access = JsonProperty.Access.READ_ONLY)
    private String status;

    @JsonProperty(value = "logs", access = JsonProperty.Access.READ_ONLY)
    @Getter(AccessLevel.NONE)
    private final List<OrderLogDTO> logs = new LinkedList<>();
    
    @JsonProperty(value = "items")
    @Getter(AccessLevel.NONE)
	private final List<OrderItemDTO> items = new LinkedList<>();
    
    @JsonProperty(value = "payment_methods")
    @Getter(AccessLevel.NONE)
	private final List<PaymentMethodDTO> paymentMethods = new LinkedList<>();

    public List<OrderLogDTO> getLogs() {
        return Collections.unmodifiableList(logs);
    }

    public List<OrderItemDTO> getItems() {
        return Collections.unmodifiableList(items);
    }

    public List<PaymentMethodDTO> getPaymentMethods() {
        return Collections.unmodifiableList(paymentMethods);
    }

    public Integer addLog(OrderLogDTO orderLog) {
		this.logs.add(orderLog);
		return this.logs.size();
	}
	
	public Integer addItem(OrderItemDTO item) {
		this.items.add(item);
		return this.items.size();
	}
	
	public Integer addPaymentMethod(PaymentMethodDTO paymentMethod) {
		this.paymentMethods.add(paymentMethod);
		return this.paymentMethods.size();
	}

}
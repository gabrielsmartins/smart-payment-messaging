package br.gabrielsmartins.smartpayment.adapters.web.adapter.in.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

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
    private final Map<LocalDateTime, String> logs = new LinkedHashMap<>();
    
    @JsonProperty(value = "items")
    @Getter(AccessLevel.NONE)
	private final List<OrderItemDTO> items = new LinkedList<>();
    
    @JsonProperty(value = "payment_methods")
    @Getter(AccessLevel.NONE)
	private final List<PaymentMethodDTO> paymentMethods = new LinkedList<>();

    public Map<LocalDateTime, String> getLogs() {
        return Collections.unmodifiableMap(logs);
    }

    public List<OrderItemDTO> getItems() {
        return Collections.unmodifiableList(items);
    }

    public List<PaymentMethodDTO> getPaymentMethods() {
        return Collections.unmodifiableList(paymentMethods);
    }

    public Integer addLog(LocalDateTime datetime, String status) {
		this.logs.put(datetime, status);
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

    @Getter
    @Setter
    @ToString
    @Builder(setterPrefix = "with")
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderItemDTO {

        @JsonProperty("product_id")
        private UUID productId;

        @JsonProperty("quantity")
        private Integer quantity;

        @JsonProperty("item_amount")
        private BigDecimal amount;

    }

    @Getter
    @Setter
    @ToString
    @Builder(setterPrefix = "with")
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PaymentMethodDTO {

        @JsonProperty("payment_type")
        private String paymentType;

        @JsonProperty("payment_amount")
        private BigDecimal amount;

    }
}
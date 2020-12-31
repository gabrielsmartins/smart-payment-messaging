package br.gabrielsmartins.smartpayment.adapters.web.adapter.in.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
public class OrderDTO {

    @JsonProperty(value = "id", access = JsonProperty.Access.WRITE_ONLY)
    private String id;

    @JsonProperty("cusomter_id")
    private UUID customerId;

    @JsonProperty("created_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime createdAt;

    @JsonProperty("finished_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime finishedAt;

    @JsonProperty("total_amount")
    private BigDecimal totalAmount;

    @JsonProperty("total_discount")
    private BigDecimal totalDiscount;
    
    @JsonProperty(value = "status", access = JsonProperty.Access.WRITE_ONLY)
    private String status;

    @JsonProperty(value = "logs", access = JsonProperty.Access.WRITE_ONLY)
    @Getter(AccessLevel.NONE)
    private final Map<LocalDateTime, String> logs = new LinkedHashMap<>();
    
    @JsonProperty(value = "items")
    @Getter(AccessLevel.NONE)
	private final List<OrderItemDTO> items = new LinkedList<>();
    
    @JsonProperty(value = "payment_methods")
    @Getter(AccessLevel.NONE)
	private final Map<String, BigDecimal> paymentMethods = new LinkedHashMap<>();

    public Map<LocalDateTime, String> getLogs() {
        return Collections.unmodifiableMap(logs);
    }


    public List<OrderItemDTO> getItems() {
        return Collections.unmodifiableList(items);
    }

    public Map<String, BigDecimal> getPaymentMethods() {
        return Collections.unmodifiableMap(paymentMethods);
    }

    public Integer addLog(LocalDateTime datetime, String status) {
		this.logs.put(datetime, status);
		return this.logs.size();
	}
	
	public Integer addItem(OrderItemDTO item) {
		this.items.add(item);
		return this.items.size();
	}
	
	public Integer addPaymentMethod(String paymentType, BigDecimal amount) {
		this.paymentMethods.put(paymentType, amount);
		return this.paymentMethods.size();
	}

    @Getter
    @Setter
    @ToString
    @Builder(setterPrefix = "with")
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderItemDTO {

        @JsonProperty("product")
        private UUID productId;

        @JsonProperty("quantity")
        private Integer quantity;

        @JsonProperty("amount")
        private BigDecimal amount;

    }
}
package br.gabrielsmartins.smartpayment.adapters.web.adapter.in.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {

    @JsonProperty(value = "id", access = JsonProperty.Access.WRITE_ONLY)
    private String id;

    @JsonProperty("cusomter_id")
    private String customerId;

    @JsonProperty("created_at")
    private LocalDate createdAt;

    @JsonProperty("finished_at")
    private LocalDate finishedAt;

    @JsonProperty("total_amount")
    private BigDecimal totalAmount;

    @JsonProperty("total_discount")
    private BigDecimal totalDiscount;
    
    @JsonProperty(value = "status", access = JsonProperty.Access.WRITE_ONLY)
    private String status;

    @JsonProperty(value = "logs", access = JsonProperty.Access.WRITE_ONLY)
    private final Map<LocalDateTime, String> logs = new LinkedHashMap<>();
    
    @JsonProperty(value = "items")
	private final Map<String, BigDecimal> items = new LinkedHashMap<>();
    
    @JsonProperty(value = "payment_methods")
	private final Map<String, BigDecimal> paymentMethods = new LinkedHashMap<>();
  
	public Integer addLog(LocalDateTime datetime, String status) {
		this.logs.put(datetime, status);
		return this.logs.size();
	}
	
	public Integer addItem(String productId, BigDecimal amount) {
		this.items.put(productId, amount);
		return this.items.size();
	}
	
	public Integer addPaymentMethod(String paymentType, BigDecimal amount) {
		this.paymentMethods.put(paymentType, amount);
		return this.paymentMethods.size();
	}
}
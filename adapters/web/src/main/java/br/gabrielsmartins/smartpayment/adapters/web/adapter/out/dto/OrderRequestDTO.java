package br.gabrielsmartins.smartpayment.adapters.web.adapter.out.dto;

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
public class OrderRequestDTO {

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

    @JsonProperty(value = "items")
    private final List<OrderRequestItemDTO> items = new LinkedList<>();

    @JsonProperty(value = "payment_methods")
    private final Map<String, BigDecimal> paymentMethods = new LinkedHashMap<>();

    public Integer addItem(OrderRequestItemDTO item) {
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
    public static class OrderRequestItemDTO {

        @JsonProperty("product")
        private UUID productId;

        @JsonProperty("quantity")
        private Integer quantity;

        @JsonProperty("amount")
        private BigDecimal amount;


    }
}

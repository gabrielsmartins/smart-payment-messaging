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
public class NotificationOrderDTO {

    @JsonProperty(value = "id", access = JsonProperty.Access.WRITE_ONLY)
    private Long id;

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
    private final List<NotificationOrderItemDTO> items = new LinkedList<>();

    @JsonProperty(value = "payment_methods")
    private final List<NotificationPaymentMethodDTO> paymentMethods = new LinkedList<>();

    public Integer addItem(NotificationOrderItemDTO item) {
        this.items.add(item);
        return this.items.size();
    }

    public Integer addPaymentMethod(NotificationPaymentMethodDTO paymentMethod) {
        this.paymentMethods.add(paymentMethod);
        return this.paymentMethods.size();
    }


}

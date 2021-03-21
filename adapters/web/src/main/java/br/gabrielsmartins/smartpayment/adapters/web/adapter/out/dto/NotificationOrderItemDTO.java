package br.gabrielsmartins.smartpayment.adapters.web.adapter.out.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@ToString
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class NotificationOrderItemDTO {

    @JsonProperty("product_id")
    private UUID productId;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("item_amount")
    private BigDecimal amount;

}
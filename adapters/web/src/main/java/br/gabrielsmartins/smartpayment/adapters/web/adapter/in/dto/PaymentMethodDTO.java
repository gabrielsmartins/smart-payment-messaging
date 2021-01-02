package br.gabrielsmartins.smartpayment.adapters.web.adapter.in.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethodDTO {

    @JsonProperty("payment_type")
    private String paymentType;

    @JsonProperty("payment_amount")
    private BigDecimal amount;

}
package br.gabrielsmartins.smartpayment.adapters.persistence.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethodEntity {

    @Transient
    private OrderEntity order;

    @Field("payment_type")
    private String paymentType;

    @JsonProperty("payment_amount")
    private BigDecimal amount;

}
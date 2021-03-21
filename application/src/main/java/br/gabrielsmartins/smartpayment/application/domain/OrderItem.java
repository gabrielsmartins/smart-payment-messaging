package br.gabrielsmartins.smartpayment.application.domain;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@ToString(exclude = {"order"})
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    private Order order;
    private UUID productId;
    private Integer quantity;
    private BigDecimal amount;

}
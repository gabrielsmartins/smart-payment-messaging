package br.gabrielsmartins.smartpayment.adapters.persistence.entity;

import lombok.*;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@ToString(exclude = {"order"})
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemEntity {

    @Transient
    private OrderEntity order;

    @Field("product_id")
    private UUID productId;

    @Field("quantity")
    private Integer quantity;

    @Field("item_amount")
    private BigDecimal amount;

}
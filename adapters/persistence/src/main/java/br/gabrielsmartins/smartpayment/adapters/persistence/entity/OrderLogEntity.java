package br.gabrielsmartins.smartpayment.adapters.persistence.entity;

import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import lombok.*;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString(exclude = {"order"})
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class OrderLogEntity {

    @Transient
    private OrderEntity order;

    @Field("datetime")
    private LocalDateTime datetime;

    @Field("status")
    private OrderStatus status;

}
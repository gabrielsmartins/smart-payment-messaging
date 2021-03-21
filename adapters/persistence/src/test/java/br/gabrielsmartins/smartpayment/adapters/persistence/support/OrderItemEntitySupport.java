package br.gabrielsmartins.smartpayment.adapters.persistence.support;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.OrderItemEntity;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderItemEntitySupport {

    private OrderItemEntitySupport(){
        super();
    }

    public static OrderItemEntity.OrderItemEntityBuilder defaultOrderItemEntity(){
        return OrderItemEntity.builder()
                .withProductId(UUID.randomUUID())
                .withQuantity(1)
                .withAmount(BigDecimal.TEN);
    }
}

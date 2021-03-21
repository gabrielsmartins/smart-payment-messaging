package br.gabrielsmartins.smartpayment.adapters.persistence.support;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.OrderEntity;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class OrderEntitySupport {

    private OrderEntitySupport(){
        super();
    }

    public static OrderEntity.OrderEntityBuilder defaultOrderEntity(){
        return OrderEntity.builder()
                .withId(12345L)
                .withCustomerId(UUID.randomUUID())
                .withCreatedAt(LocalDateTime.now())
                .withFinishedAt(LocalDateTime.now())
                .withStatus(OrderStatus.COMPLETED)
                .withTotalAmount(BigDecimal.valueOf(1500.00))
                .withTotalDiscount(BigDecimal.valueOf(1400.00));
    }
}

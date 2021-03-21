package br.gabrielsmartins.smartpayment.application.support;

import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class OrderSupport {

    private OrderSupport(){
        super();
    }

    public static Order.OrderBuilder defaultOrder(){
        return Order.builder()
                .withId(12345L)
                .withCustomerId(UUID.randomUUID())
                .withCreatedAt(LocalDateTime.now())
                .withFinishedAt(LocalDateTime.now())
                .withStatus(OrderStatus.NEW)
                .withTotalAmount(BigDecimal.valueOf(1500.00))
                .withTotalDiscount(BigDecimal.valueOf(1400.00))
                .withFinishedAt(LocalDateTime.now());
    }
}

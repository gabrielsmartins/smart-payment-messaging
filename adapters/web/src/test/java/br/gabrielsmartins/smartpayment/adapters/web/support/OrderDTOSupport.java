package br.gabrielsmartins.smartpayment.adapters.web.support;

import br.gabrielsmartins.smartpayment.adapters.web.adapter.in.dto.OrderDTO;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class OrderDTOSupport {

    private OrderDTOSupport(){
        super();
    }

    public static OrderDTO.OrderDTOBuilder defaultOrderDto(){
        return OrderDTO.builder()
                .withId(12345L)
                .withCustomerId(UUID.randomUUID())
                .withCreatedAt(LocalDateTime.now())
                .withFinishedAt(LocalDateTime.now())
                .withStatus(OrderStatus.COMPLETED.getDescription())
                .withTotalAmount(BigDecimal.valueOf(1500.00))
                .withTotalDiscount(BigDecimal.valueOf(1400.00));
    }
}

package br.gabrielsmartins.smartpayment.adapters.web.support;

import br.gabrielsmartins.smartpayment.adapters.web.adapter.in.dto.OrderItemDTO;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderItemDTOSupport {

    private OrderItemDTOSupport(){
        super();
    }

    public static OrderItemDTO.OrderItemDTOBuilder defaultOrderItemDto(){
        return OrderItemDTO.builder()
                .withProductId(UUID.randomUUID())
                .withQuantity(1)
                .withAmount(BigDecimal.TEN);
    }
}

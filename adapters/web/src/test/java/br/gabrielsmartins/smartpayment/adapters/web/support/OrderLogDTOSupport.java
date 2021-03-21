package br.gabrielsmartins.smartpayment.adapters.web.support;

import br.gabrielsmartins.smartpayment.adapters.web.adapter.in.dto.OrderLogDTO;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;

import java.time.LocalDateTime;

public class OrderLogDTOSupport {

    private OrderLogDTOSupport(){
        super();
    }

    public static OrderLogDTO.OrderLogDTOBuilder defaultOrderLogDto(){
        return OrderLogDTO.builder()
                .withStatus(OrderStatus.COMPLETED.getDescription())
                .withDatetime(LocalDateTime.now());
    }
}

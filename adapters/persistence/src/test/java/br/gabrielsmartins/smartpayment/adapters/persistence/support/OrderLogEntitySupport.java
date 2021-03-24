package br.gabrielsmartins.smartpayment.adapters.persistence.support;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.OrderLogEntity;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;

import java.time.LocalDateTime;

public class OrderLogEntitySupport {

    private OrderLogEntitySupport(){
        super();
    }

    public static OrderLogEntity.OrderLogEntityBuilder defaultOrderLogEntity(){
        return OrderLogEntity.builder()
                .withStatus(OrderStatus.RECEIVED)
                .withDatetime(LocalDateTime.now());
    }
}

package br.gabrielsmartins.smartpayment.application.support;

import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import br.gabrielsmartins.smartpayment.application.domain.state.OrderLog;

import java.time.LocalDateTime;

public class OrderLogSupport {

    private OrderLogSupport(){
        super();
    }

    public static OrderLog.OrderLogBuilder defaultOrderLog(){
        return OrderLog.builder()
                .withStatus(OrderStatus.COMPLETED)
                .withDatetime(LocalDateTime.now());
    }

}

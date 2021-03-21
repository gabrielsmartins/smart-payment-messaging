package br.gabrielsmartins.smartpayment.adapters.messaging.adapter.out.mapper.confirmed;


import br.gabrielsmartins.schemas.order_confirmed.OrderConfirmed;
import br.gabrielsmartins.smartpayment.adapters.messaging.adapter.out.mapper.factory.OrderStatusMessagingProducerMapper;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ConfirmedOrderStatusMessagingProducerMapper implements OrderStatusMessagingProducerMapper<OrderConfirmed> {

    @Override
    public OrderConfirmed mapToMessage(Order order) {
        return OrderConfirmed.newBuilder()
                .setId(order.getId())
                .setCustomerId(order.getCustomerId().toString())
                .setCreatedAt(order.getCreatedAt())
                .setConfirmedAt(LocalDateTime.now())
                .setTotalAmount(order.getTotalAmount())
                .setTotalDiscount(order.getTotalDiscount())
                .build();
    }

    @Override
    public OrderStatus getOrderStatus() {
        return OrderStatus.CONFIRMED;
    }
}

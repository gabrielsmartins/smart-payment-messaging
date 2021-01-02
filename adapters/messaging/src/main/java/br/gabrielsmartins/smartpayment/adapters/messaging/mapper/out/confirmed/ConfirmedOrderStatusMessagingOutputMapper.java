package br.gabrielsmartins.smartpayment.adapters.messaging.mapper.out.confirmed;


import br.gabrielsmartins.schemas.order_confirmed.OrderConfirmed;
import br.gabrielsmartins.smartpayment.adapters.messaging.mapper.out.factory.OrderStatusMessagingOutputMapper;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConfirmedOrderStatusMessagingOutputMapper implements OrderStatusMessagingOutputMapper<OrderConfirmed> {

    @Override
    public OrderConfirmed mapToMessage(Order order) {
        return OrderConfirmed.newBuilder()
                .setId(order.getId())
                .setCustomerId(order.getCustomerId().toString())
                .setCreatedAt(order.getCreatedAt())
                .setTotalAmount(order.getTotalAmount())
                .setTotalDiscount(order.getTotalDiscount())
                .build();
    }

    @Override
    public OrderStatus getOrderStatus() {
        return OrderStatus.CONFIRMED;
    }
}

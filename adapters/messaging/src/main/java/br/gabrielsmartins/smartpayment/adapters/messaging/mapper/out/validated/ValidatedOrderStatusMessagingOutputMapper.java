package br.gabrielsmartins.smartpayment.adapters.messaging.mapper.out.validated;


import br.gabrielsmartins.schemas.order_validated.OrderValidated;
import br.gabrielsmartins.smartpayment.adapters.messaging.mapper.out.factory.OrderStatusMessagingOutputMapper;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidatedOrderStatusMessagingOutputMapper implements OrderStatusMessagingOutputMapper<OrderValidated> {

    @Override
    public OrderValidated mapToMessage(Order order) {

        return OrderValidated.newBuilder()
                .setId(order.getId())
                .setCustomerId(order.getCustomerId().toString())
                .setCreatedAt(order.getCreatedAt())
                .setTotalAmount(order.getTotalAmount())
                .setTotalDiscount(order.getTotalDiscount())
                .build();
    }

    @Override
    public OrderStatus getOrderStatus() {
        return OrderStatus.VALIDATED;
    }
}

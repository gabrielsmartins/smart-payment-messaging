package br.gabrielsmartins.smartpayment.adapters.messaging.mapper.out;


import br.gabrielsmartins.schemas.order_validated.OrderValidated;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidatedOrderStatusMapper implements OrderStatusMapper<OrderValidated> {

    @Override
    public OrderValidated mapToMessage(Order order) {

        return OrderValidated.newBuilder()
                .setId(order.getId().toString())
                .setCustomerId(order.getCustomerId().toString())
                .setCreatedAt(order.getCreatedAt())
                .setTotalAmount(order.getTotalAmount())
                .setTotalDiscount(order.getTotalDiscount())
                .build();
    }
}

package br.gabrielsmartins.smartpayment.adapters.messaging.adapter.in.orders.mapper;


import br.gabrielsmartins.schemas.order_requested.OrderRequested;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderMessagingConsumerMapper {

    private final OrderItemMessagingConsumerMapper orderItemMapper;
    private final PaymentMethodMessagingConsumerMapper paymentMethodMapper;

    public Order mapToDomain(OrderRequested orderMessage){
        Order order = new Order();
        order.setCustomerId(UUID.fromString(orderMessage.getCustomerId()));
        order.setCreatedAt(orderMessage.getCreatedAt());
        order.setTotalAmount(orderMessage.getTotalAmount());
        order.setTotalDiscount(orderMessage.getTotalDiscount());
        orderMessage.getItems().stream()
                               .map(orderItemMapper::mapToDomain)
                               .forEach(order::addItem);

        orderMessage.getPaymentMethods().stream()
                                        .map(paymentMethodMapper::mapToDomain)
                                        .forEach(order::addPaymentMethod);
        return order;
    }

}

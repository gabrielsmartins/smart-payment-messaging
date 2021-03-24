package br.gabrielsmartins.smartpayment.adapters.messaging.adapter.in.orders.mapper;


import br.gabrielsmartins.schemas.order_requested.Item;
import br.gabrielsmartins.smartpayment.application.domain.OrderItem;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderItemMessagingConsumerMapper {

    public OrderItem mapToDomain(Item item){
        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(UUID.fromString(item.getProductId()));
        orderItem.setQuantity(item.getQuantity());
        orderItem.setAmount(item.getAmount());
        return orderItem;
    }

}

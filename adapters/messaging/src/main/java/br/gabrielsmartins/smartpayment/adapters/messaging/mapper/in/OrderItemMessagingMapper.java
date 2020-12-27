package br.gabrielsmartins.smartpayment.adapters.messaging.mapper.in;


import br.gabrielsmartins.schemas.new_order.Item;
import br.gabrielsmartins.smartpayment.application.domain.Order.OrderItem;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderItemMessagingMapper {

    public OrderItem mapToDomain(Item item){
        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(UUID.fromString(item.getProductId()));
        orderItem.setQuantity(item.getQuantity());
        orderItem.setAmount(item.getAmount());
        return orderItem;
    }

}

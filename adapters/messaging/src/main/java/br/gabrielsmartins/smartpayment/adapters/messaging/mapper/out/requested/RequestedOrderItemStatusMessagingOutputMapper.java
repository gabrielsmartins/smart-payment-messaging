package br.gabrielsmartins.smartpayment.adapters.messaging.mapper.out.requested;

import br.gabrielsmartins.schemas.order_requested.Item;
import br.gabrielsmartins.smartpayment.application.domain.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class RequestedOrderItemStatusMessagingOutputMapper {

    public Item mapToMessage(OrderItem orderItem) {
        return Item.newBuilder()
                .setProductId(orderItem.getProductId().toString())
                .setQuantity(orderItem.getQuantity())
                .setAmount(orderItem.getAmount())
                .build();
    }
}

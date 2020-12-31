package br.gabrielsmartins.smartpayment.adapters.messaging.mapper.out;

import br.gabrielsmartins.schemas.order_requested.Item;
import br.gabrielsmartins.smartpayment.application.domain.Order.*;

public class RequestedOrderItemStatusMapper {

    public Item mapToMessage(OrderItem orderItem) {
        return Item.newBuilder()
                .setProductId(orderItem.getProductId().toString())
                .setQuantity(orderItem.getQuantity())
                .setAmount(orderItem.getAmount())
                .build();
    }
}

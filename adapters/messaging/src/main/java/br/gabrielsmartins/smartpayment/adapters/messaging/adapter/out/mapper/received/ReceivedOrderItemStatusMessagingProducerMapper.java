package br.gabrielsmartins.smartpayment.adapters.messaging.adapter.out.mapper.received;

import br.gabrielsmartins.schemas.order_received.Item;
import br.gabrielsmartins.smartpayment.application.domain.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class ReceivedOrderItemStatusMessagingProducerMapper {

    public Item mapToMessage(OrderItem orderItem) {
        return Item.newBuilder()
                .setProductId(orderItem.getProductId().toString())
                .setQuantity(orderItem.getQuantity())
                .setAmount(orderItem.getAmount())
                .build();
    }
}

package br.gabrielsmartins.smartpayment.adapters.messaging.adapter.out.mapper.validated;

import br.gabrielsmartins.schemas.order_validated.Item;
import br.gabrielsmartins.smartpayment.application.domain.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class ValidatedOrderItemStatusMessagingProducerMapper {

    public Item mapToMessage(OrderItem orderItem) {
        return Item.newBuilder()
                .setProductId(orderItem.getProductId().toString())
                .setQuantity(orderItem.getQuantity())
                .setAmount(orderItem.getAmount())
                .build();
    }
}

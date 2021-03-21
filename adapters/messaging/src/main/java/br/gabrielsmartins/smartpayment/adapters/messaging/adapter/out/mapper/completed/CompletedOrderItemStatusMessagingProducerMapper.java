package br.gabrielsmartins.smartpayment.adapters.messaging.adapter.out.mapper.completed;


import br.gabrielsmartins.schemas.order_completed.Item;
import br.gabrielsmartins.smartpayment.application.domain.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class CompletedOrderItemStatusMessagingProducerMapper {

    public Item mapToMessage(OrderItem orderItem) {
        return Item.newBuilder()
                   .setProductId(orderItem.getProductId().toString())
                    .setQuantity(orderItem.getQuantity())
                    .setAmount(orderItem.getAmount())
                .build();
    }
}

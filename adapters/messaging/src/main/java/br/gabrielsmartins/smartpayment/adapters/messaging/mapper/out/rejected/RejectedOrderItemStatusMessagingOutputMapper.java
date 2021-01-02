package br.gabrielsmartins.smartpayment.adapters.messaging.mapper.out.rejected;


import br.gabrielsmartins.schemas.order_rejected.Item;
import br.gabrielsmartins.smartpayment.application.domain.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class RejectedOrderItemStatusMessagingOutputMapper {

    public Item mapToMessage(OrderItem orderItem) {
        return Item.newBuilder()
                   .setProductId(orderItem.getProductId().toString())
                    .setQuantity(orderItem.getQuantity())
                    .setAmount(orderItem.getAmount())
                .build();
    }
}

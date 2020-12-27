package br.gabrielsmartins.smartpayment.adapters.messaging.mapper.out;


import br.gabrielsmartins.schemas.order_rejected.Item;
import br.gabrielsmartins.smartpayment.application.domain.Order.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class RejectedOrderItemStatusMapper {

    public Item mapToMessage(OrderItem orderItem) {
        return Item.newBuilder()
                   .setProductId(orderItem.getProductId().toString())
                    .setQuantity(orderItem.getQuantity())
                    .setAmount(orderItem.getAmount())
                .build();
    }
}

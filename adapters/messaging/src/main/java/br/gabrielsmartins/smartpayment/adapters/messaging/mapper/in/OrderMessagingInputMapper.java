package br.gabrielsmartins.smartpayment.adapters.messaging.mapper.in;

import br.gabrielsmartins.schemas.new_order.NewOrder;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderMessagingInputMapper {

    private final OrderItemMessagingInputMapper orderItemMapper;
    private final PaymentMethodMessagingInputMapper paymentMethodMapper;

    public Order mapToDomain(NewOrder orderMessage){
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

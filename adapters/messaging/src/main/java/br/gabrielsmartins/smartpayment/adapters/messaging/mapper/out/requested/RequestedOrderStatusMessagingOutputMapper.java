package br.gabrielsmartins.smartpayment.adapters.messaging.mapper.out.requested;


import br.gabrielsmartins.schemas.order_requested.Item;
import br.gabrielsmartins.schemas.order_requested.OrderRequested;
import br.gabrielsmartins.schemas.order_requested.PaymentMethod;
import br.gabrielsmartins.smartpayment.adapters.messaging.mapper.out.factory.OrderStatusMessagingOutputMapper;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RequestedOrderStatusMessagingOutputMapper implements OrderStatusMessagingOutputMapper<OrderRequested> {

    private final RequestedOrderItemStatusMessagingOutputMapper itemMapper;
    private final RequestedOrderStatusPaymentMethodMessagingOutputMapper paymentMethodMapper;

    @Override
    public OrderRequested mapToMessage(Order order) {
        List<Item> itemsMessage = order.getItems().stream()
                                            .map(itemMapper::mapToMessage)
                                            .collect(Collectors.toList());

        List<PaymentMethod> paymentMethodsMessage = order.getPaymentMethods().stream()
                                                            .map(paymentMethodMapper::mapToMessage)
                                                            .collect(Collectors.toList());

        return OrderRequested.newBuilder()
                .setId(order.getId())
                .setCustomerId(order.getCustomerId().toString())
                .setCreatedAt(order.getCreatedAt())
                .setTotalAmount(order.getTotalAmount())
                .setTotalDiscount(order.getTotalDiscount())
                .setItems(itemsMessage)
                .setPaymentMethods(paymentMethodsMessage)
                .build();
    }

    @Override
    public OrderStatus getOrderStatus() {
        return OrderStatus.REQUESTED;
    }
}

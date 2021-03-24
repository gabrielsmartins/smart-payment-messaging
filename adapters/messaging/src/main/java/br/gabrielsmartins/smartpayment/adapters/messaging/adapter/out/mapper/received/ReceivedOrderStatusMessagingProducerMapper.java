package br.gabrielsmartins.smartpayment.adapters.messaging.adapter.out.mapper.received;


import br.gabrielsmartins.schemas.order_received.Item;
import br.gabrielsmartins.schemas.order_received.OrderReceived;
import br.gabrielsmartins.schemas.order_received.PaymentMethod;
import br.gabrielsmartins.smartpayment.adapters.messaging.adapter.out.mapper.factory.OrderStatusMessagingProducerMapper;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ReceivedOrderStatusMessagingProducerMapper implements OrderStatusMessagingProducerMapper<OrderReceived> {

    private final ReceivedOrderItemStatusMessagingProducerMapper itemMapper;
    private final ReceivedOrderStatusPaymentMethodMessagingProducerMapper paymentMethodMapper;

    @Override
    public OrderReceived mapToMessage(Order order) {
        List<Item> itemsMessage = order.getItems().stream()
                                            .map(itemMapper::mapToMessage)
                                            .collect(Collectors.toList());

        List<PaymentMethod> paymentMethodsMessage = order.getPaymentMethods().stream()
                                                            .map(paymentMethodMapper::mapToMessage)
                                                            .collect(Collectors.toList());

        return OrderReceived.newBuilder()
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
        return OrderStatus.RECEIVED;
    }
}

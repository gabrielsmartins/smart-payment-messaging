package br.gabrielsmartins.smartpayment.adapters.messaging.adapter.out.mapper.rejected;


import br.gabrielsmartins.schemas.order_rejected.Item;
import br.gabrielsmartins.schemas.order_rejected.OrderRejected;
import br.gabrielsmartins.schemas.order_rejected.PaymentMethod;
import br.gabrielsmartins.smartpayment.adapters.messaging.adapter.out.mapper.factory.OrderStatusMessagingProducerMapper;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RejectedOrderStatusMessagingProducerMapper implements OrderStatusMessagingProducerMapper<OrderRejected> {

    private final RejectedOrderItemStatusMessagingProducerMapper itemMapper;
    private final RejectedOrderStatusPaymentMethodMessagingProducerMapper paymentMethodMapper;

    @Override
    public OrderRejected mapToMessage(Order order) {
        List<Item> itemsMessage = order.getItems().stream()
                                            .map(itemMapper::mapToMessage)
                                            .collect(Collectors.toList());

        List<PaymentMethod> paymentMethodsMessage = order.getPaymentMethods().stream()
                                                        .map(paymentMethodMapper::mapToMessage)
                                                        .collect(Collectors.toList());

        return OrderRejected.newBuilder()
                .setId(order.getId())
                .setCustomerId(order.getCustomerId().toString())
                .setCreatedAt(order.getCreatedAt())
                .setTotalAmount(order.getTotalAmount())
                .setTotalDiscount(order.getTotalDiscount())
                .setItems(itemsMessage)
                .setPaymentMethods(paymentMethodsMessage)
                .setFinishedAt(order.getFinishedAt())
                .build();
    }

    @Override
    public OrderStatus getOrderStatus() {
        return OrderStatus.REJECTED;
    }
}

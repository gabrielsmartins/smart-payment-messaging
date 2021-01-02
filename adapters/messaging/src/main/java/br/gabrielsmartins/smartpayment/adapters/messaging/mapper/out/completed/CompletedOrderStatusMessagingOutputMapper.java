package br.gabrielsmartins.smartpayment.adapters.messaging.mapper.out.completed;


import br.gabrielsmartins.schemas.order_completed.Item;
import br.gabrielsmartins.schemas.order_completed.OrderCompleted;
import br.gabrielsmartins.schemas.order_completed.PaymentMethod;
import br.gabrielsmartins.smartpayment.adapters.messaging.mapper.out.factory.OrderStatusMessagingOutputMapper;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CompletedOrderStatusMessagingOutputMapper implements OrderStatusMessagingOutputMapper<OrderCompleted> {

    private final CompletedOrderItemStatusMessagingOutputMapper itemMapper;
    private final CompletedOrderStatusPaymentMethodMessagingOutputMapper paymentMethodMapper;

    @Override
    public OrderCompleted mapToMessage(Order order) {
        List<Item> itemsMessage = order.getItems().stream()
                                            .map(itemMapper::mapToMessage)
                                            .collect(Collectors.toList());

        List<PaymentMethod> paymentMethodsMessage = order.getPaymentMethods().stream()
                                                         .map(paymentMethodMapper::mapToMessage)
                                                         .collect(Collectors.toList());

        return OrderCompleted.newBuilder()
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
        return OrderStatus.COMPLETED;
    }
}

package br.gabrielsmartins.smartpayment.adapters.messaging.mapper.out;


import br.gabrielsmartins.schemas.order_completed.Item;
import br.gabrielsmartins.schemas.order_completed.OrderCompleted;
import br.gabrielsmartins.schemas.order_completed.PaymentMethod;
import br.gabrielsmartins.schemas.order_completed.PaymentType;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CompletedOrderStatusMapper implements OrderStatusMapper<OrderCompleted> {

    private final CompletedOrderItemStatusMapper itemMapper;

    @Override
    public OrderCompleted mapToMessage(Order order) {
        List<Item> items = order.getItems().stream()
                                            .map(itemMapper::mapToMessage)
                                            .collect(Collectors.toList());

        List<PaymentMethod> paymentMethods = order.getPaymentMethods().entrySet()
                                                                      .stream()
                                                                      .map(it -> PaymentMethod.newBuilder()
                                                                                  .setPaymentType(PaymentType.valueOf(it.getKey().name()))
                                                                                  .setAmount(it.getValue())
                                                                                .build())
                                                                     .collect(Collectors.toList());

        return OrderCompleted.newBuilder()
                .setId(order.getId().toString())
                .setCustomerId(order.getCustomerId().toString())
                .setCreatedAt(order.getCreatedAt())
                .setTotalAmount(order.getTotalAmount())
                .setTotalDiscount(order.getTotalDiscount())
                .setItems(items)
                .setPaymentMethods(paymentMethods)
                .setFinishedAt(order.getFinishedAt())
                .build();
    }

    @Override
    public OrderStatus getOrderStatus() {
        return OrderStatus.COMPLETED;
    }
}

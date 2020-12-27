package br.gabrielsmartins.smartpayment.adapters.messaging.mapper.out;


import br.gabrielsmartins.schemas.order_requested.Item;
import br.gabrielsmartins.schemas.order_requested.OrderRequested;
import br.gabrielsmartins.schemas.order_requested.PaymentMethod;
import br.gabrielsmartins.schemas.order_requested.PaymentType;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RequestedOrderStatusMapper implements OrderStatusMapper<OrderRequested> {

    private final RequestedOrderItemStatusMapper itemMapper;

    @Override
    public OrderRequested mapToMessage(Order order) {
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

        return OrderRequested.newBuilder()
                .setId(order.getId().toString())
                .setCustomerId(order.getCustomerId().toString())
                .setCreatedAt(order.getCreatedAt())
                .setTotalAmount(order.getTotalAmount())
                .setTotalDiscount(order.getTotalDiscount())
                .setItems(items)
                .setPaymentMethods(paymentMethods)
                .build();
    }
}

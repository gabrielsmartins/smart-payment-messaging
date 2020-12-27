package br.gabrielsmartins.smartpayment.adapters.messaging.mapper.out;


import br.gabrielsmartins.schemas.order_rejected.Item;
import br.gabrielsmartins.schemas.order_rejected.PaymentMethod;
import br.gabrielsmartins.schemas.order_rejected.PaymentType;
import br.gabrielsmartins.schemas.order_rejected.OrderRejected;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RejectedOrderStatusMapper implements OrderStatusMapper<OrderRejected> {

    private final RejectedOrderItemStatusMapper itemMapper;

    @Override
    public OrderRejected mapToMessage(Order order) {
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

        return OrderRejected.newBuilder()
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

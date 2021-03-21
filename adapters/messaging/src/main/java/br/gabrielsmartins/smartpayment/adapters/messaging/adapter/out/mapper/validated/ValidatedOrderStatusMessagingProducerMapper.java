package br.gabrielsmartins.smartpayment.adapters.messaging.adapter.out.mapper.validated;


import br.gabrielsmartins.schemas.order_validated.Item;
import br.gabrielsmartins.schemas.order_validated.PaymentMethod;
import br.gabrielsmartins.schemas.order_validated.OrderValidated;
import br.gabrielsmartins.smartpayment.adapters.messaging.adapter.out.mapper.factory.OrderStatusMessagingProducerMapper;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ValidatedOrderStatusMessagingProducerMapper implements OrderStatusMessagingProducerMapper<OrderValidated> {

    private final ValidatedOrderItemStatusMessagingProducerMapper itemMapper;
    private final ValidatedOrderStatusPaymentMethodMessagingProducerMapper paymentMethodMapper;

    @Override
    public OrderValidated mapToMessage(Order order) {
        List<Item> itemsMessage = order.getItems().stream()
                .map(itemMapper::mapToMessage)
                .collect(Collectors.toList());

        List<PaymentMethod> paymentMethodsMessage = order.getPaymentMethods().stream()
                .map(paymentMethodMapper::mapToMessage)
                .collect(Collectors.toList());
        return OrderValidated.newBuilder()
                .setId(order.getId())
                .setCustomerId(order.getCustomerId().toString())
                .setCreatedAt(order.getCreatedAt())
                .setTotalAmount(order.getTotalAmount())
                .setTotalDiscount(order.getTotalDiscount())
                .setItems(itemsMessage)
                .setPaymentMethods(paymentMethodsMessage)
                .setValidatedAt(LocalDateTime.now())
                .build();
    }

    @Override
    public OrderStatus getOrderStatus() {
        return OrderStatus.VALIDATED;
    }
}

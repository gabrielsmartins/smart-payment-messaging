package br.gabrielsmartins.smartpayment.adapters.messaging.mapper.in;

import br.gabrielsmartins.schemas.new_order.NewOrder;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.enums.PaymentType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderMessagingMapper {

    private final OrderItemMessagingMapper orderItemMapper;

    public Order mapToDomain(NewOrder orderMessage){
        Order order = new Order();
        order.setCustomerId(UUID.fromString(orderMessage.getCustomerId()));
        order.setCreatedAt(orderMessage.getCreatedAt());
        order.setTotalAmount(orderMessage.getTotalAmount());
        order.setTotalDiscount(orderMessage.getTotalDiscount());
        orderMessage.getItems().stream()
                               .map(orderItemMapper::mapToDomain)
                               .forEach(order::addItem);
        orderMessage.getPaymentMethods()
                    .forEach(pm -> {
                        PaymentType paymentType = PaymentType.valueOf(pm.getPaymentType().name());
                        BigDecimal amount = pm.getAmount();
                        order.addPaymentMethod(paymentType, amount);
                    });
        return order;
    }

}

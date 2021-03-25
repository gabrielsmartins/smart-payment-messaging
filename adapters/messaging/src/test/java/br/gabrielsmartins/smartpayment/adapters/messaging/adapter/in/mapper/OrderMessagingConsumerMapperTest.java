package br.gabrielsmartins.smartpayment.adapters.messaging.adapter.in.mapper;


import br.gabrielsmartins.schemas.order_requested.Item;
import br.gabrielsmartins.schemas.order_requested.OrderRequested;
import br.gabrielsmartins.schemas.order_requested.PaymentMethod;
import br.gabrielsmartins.schemas.order_requested.PaymentType;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderMessagingConsumerMapperTest {

    private OrderMessagingConsumerMapper mapper;

    @BeforeEach
    public void setup(){
        OrderItemMessagingConsumerMapper orderItemMapper = new OrderItemMessagingConsumerMapper();
        PaymentMethodMessagingConsumerMapper paymentMethodMapper = new PaymentMethodMessagingConsumerMapper();
        this.mapper = new OrderMessagingConsumerMapper(orderItemMapper, paymentMethodMapper);
    }

    @Test
    @DisplayName("Given Message When Map Then Return Order")
    public void givenMessageWhenMapThenReturnOrder(){

        var message = OrderRequested.newBuilder()
                .setCreatedAt(LocalDateTime.now())
                .setCustomerId(UUID.randomUUID().toString())
                .setTotalAmount(BigDecimal.valueOf(1500.00))
                .setTotalDiscount(BigDecimal.valueOf(1000.00))
                .setItems(List.of(Item.newBuilder()
                        .setProductId(UUID.randomUUID().toString())
                        .setQuantity(1)
                        .setAmount(BigDecimal.valueOf(1000))
                        .build()))
                .setPaymentMethods(List.of(PaymentMethod.newBuilder()
                        .setAmount(BigDecimal.valueOf(1000.00))
                        .setPaymentType(PaymentType.CREDIT_CARD)
                        .build()))
                .build();

        Order order = this.mapper.mapToDomain(message);

        assertThat(order).hasNoNullFieldsOrPropertiesExcept("id", "finishedAt");
        assertThat(order.getCreatedAt()).isEqualTo(message.getCreatedAt());
        assertThat(order.getCustomerId().toString()).isEqualTo(message.getCustomerId());
        assertThat(order.getTotalAmount()).isEqualTo(message.getTotalAmount());
        assertThat(order.getTotalDiscount()).isEqualTo(message.getTotalDiscount());
        assertThat(order.getItems().size()).isEqualTo(message.getItems().size());
        assertThat(order.getPaymentMethods().size()).isEqualTo(message.getPaymentMethods().size());
    }
}

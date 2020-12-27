package br.gabrielsmartins.smartpayment.adapters.messaging.mapper.in;


import br.gabrielsmartins.schemas.new_order.Item;
import br.gabrielsmartins.smartpayment.application.domain.Order.OrderItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderItemMessagingMapperTest {

    private OrderItemMessagingMapper mapper;

    @BeforeEach
    public void setup(){
        this.mapper = new OrderItemMessagingMapper();
    }

    @Test
    @DisplayName("Given Item Message When Map Then Return Order Item")
    public void givenItemMessageWhenMapThenReturnOrderItem(){

        Item item = Item.newBuilder()
                .setProductId(UUID.randomUUID().toString())
                .setQuantity(1)
                .setAmount(BigDecimal.valueOf(100.00))
                .build();

        OrderItem orderItem = this.mapper.mapToDomain(item);

        assertThat(orderItem).hasNoNullFieldsOrPropertiesExcept("order");
        assertThat(orderItem.getProductId().toString()).isEqualTo(item.getProductId());
        assertThat(orderItem.getQuantity()).isEqualTo(item.getQuantity());
        assertThat(orderItem.getAmount()).isEqualTo(item.getAmount());
    }
}

package br.gabrielsmartins.smartpayment.adapters.messaging.mapper.out;

import br.gabrielsmartins.schemas.order_requested.Item;
import br.gabrielsmartins.smartpayment.application.domain.Order.OrderItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class RejectedOrderItemStatusMapperTest {

    private RequestedOrderItemStatusMapper mapper;

    @BeforeEach
    public void setup(){
        this.mapper = new RequestedOrderItemStatusMapper();
    }

    @Test
    @DisplayName("Given Order Item When Map Then Return Item Message")
    public void givenOrderItemWhenMapThenReturnItemMessage(){

        OrderItem orderItem = OrderItem.builder()
                .withProductId(UUID.randomUUID())
                .withQuantity(1)
                .withAmount(BigDecimal.valueOf(100.00))
                .build();

        Item item = this.mapper.mapToMessage(orderItem);

        assertThat(item.getProductId()).isEqualTo(orderItem.getProductId().toString());
        assertThat(item.getQuantity()).isEqualTo(orderItem.getQuantity());
        assertThat(item.getAmount()).isEqualTo(orderItem.getAmount());
    }
}

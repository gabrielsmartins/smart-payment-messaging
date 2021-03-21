package br.gabrielsmartins.smartpayment.adapters.messaging.adapter.out.mapper.rejected;

import br.gabrielsmartins.schemas.order_rejected.Item;
import br.gabrielsmartins.smartpayment.application.domain.OrderItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.gabrielsmartins.smartpayment.application.support.OrderItemSupport.defaultOrderItem;
import static org.assertj.core.api.Assertions.assertThat;

public class RejectedOrderItemStatusMessagingProducerMapperTest {

    private RejectedOrderItemStatusMessagingProducerMapper mapper;

    @BeforeEach
    public void setup(){
        this.mapper = new RejectedOrderItemStatusMessagingProducerMapper();
    }

    @Test
    @DisplayName("Given Order Item When Map Then Return Item Message")
    public void givenOrderItemWhenMapThenReturnItemMessage(){

        OrderItem orderItem = defaultOrderItem().build();

        Item item = this.mapper.mapToMessage(orderItem);

        assertThat(item.getProductId()).isEqualTo(orderItem.getProductId().toString());
        assertThat(item.getQuantity()).isEqualTo(orderItem.getQuantity());
        assertThat(item.getAmount()).isEqualTo(orderItem.getAmount());
    }
}

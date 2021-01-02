package br.gabrielsmartins.smartpayment.adapters.web.mapper.in;

import br.gabrielsmartins.smartpayment.adapters.web.adapter.in.dto.OrderItemDTO;
import br.gabrielsmartins.smartpayment.application.domain.OrderItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderItemWebMapperTest {

    private OrderItemWebMapper mapper;

    @BeforeEach
    public void setup() {
        this.mapper = new OrderItemWebMapperImpl();
    }

    @Test
    @DisplayName("Given Order Item When Map Then Return Order Item Dto")
    public void givenOrderItemWhenMapThenReturnOrderItemDto(){

        OrderItem item = OrderItem.builder()
                .withProductId(UUID.randomUUID())
                .withQuantity(1)
                .withAmount(BigDecimal.TEN)
                .build();

        OrderItemDTO itemDTO = this.mapper.mapToDto(item);

        assertThat(itemDTO).hasNoNullFieldsOrProperties();
        assertThat(itemDTO.getProductId()).isEqualTo(item.getProductId());
        assertThat(itemDTO.getAmount()).isEqualTo(item.getAmount());
        assertThat(itemDTO.getQuantity()).isEqualTo(item.getQuantity());
    }

    @Test
    @DisplayName("Given Order Item Dto When Map Then Return Order Item")
    public void givenOrderItemDtoWhenMapThenReturnOrderItem(){

        OrderItemDTO itemDTO = OrderItemDTO.builder()
                .withProductId(UUID.randomUUID())
                .withQuantity(1)
                .withAmount(BigDecimal.TEN)
                .build();

        OrderItem item = this.mapper.mapToDomain(itemDTO);

        assertThat(item).hasNoNullFieldsOrPropertiesExcept("order");
        assertThat(item.getProductId()).isEqualTo(itemDTO.getProductId());
        assertThat(item.getAmount()).isEqualTo(itemDTO.getAmount());
        assertThat(item.getQuantity()).isEqualTo(itemDTO.getQuantity());
    }


}

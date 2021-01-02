package br.gabrielsmartins.smartpayment.adapters.persistence.mapper;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.OrderItemEntity;
import br.gabrielsmartins.smartpayment.application.domain.OrderItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderItemPersistenceMapperTest {

    private OrderItemPersistenceMapper mapper;

    @BeforeEach
    public void setup(){
        this.mapper = new OrderItemPersistenceMapperImpl();
    }

    @Test
    @DisplayName("Given Order Item When Map Then Return Order Item Entity")
    public void givenOrderItemWhenMapThenReturnOrderItemEntity(){
        OrderItem orderItem = OrderItem.builder()
                                        .withProductId(UUID.randomUUID())
                                        .withQuantity(1)
                                        .withAmount(BigDecimal.TEN)
                                        .build();

        OrderItemEntity orderItemEntity = this.mapper.mapToEntity(orderItem);

        assertThat(orderItemEntity).hasNoNullFieldsOrPropertiesExcept("order");
        assertThat(orderItemEntity.getProductId()).isEqualTo(orderItem.getProductId());
        assertThat(orderItemEntity.getQuantity()).isEqualTo(orderItem.getQuantity());
        assertThat(orderItemEntity.getAmount()).isEqualTo(orderItem.getAmount());
    }

    @Test
    @DisplayName("Given Order Item Entity When Map Then Return Order Item")
    public void givenOrderItemEntityWhenMapThenReturnOrderItem(){
        OrderItemEntity orderItemEntity = OrderItemEntity.builder()
                .withProductId(UUID.randomUUID())
                .withQuantity(1)
                .withAmount(BigDecimal.TEN)
                .build();

        OrderItem orderItem = this.mapper.mapToDomain(orderItemEntity);

        assertThat(orderItem).hasNoNullFieldsOrPropertiesExcept("order");
        assertThat(orderItem.getProductId()).isEqualTo(orderItemEntity.getProductId());
        assertThat(orderItem.getQuantity()).isEqualTo(orderItemEntity.getQuantity());
        assertThat(orderItem.getAmount()).isEqualTo(orderItemEntity.getAmount());
    }
}

package br.gabrielsmartins.smartpayment.adapters.persistence.mapper;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.OrderItemEntity;
import br.gabrielsmartins.smartpayment.application.domain.OrderItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.gabrielsmartins.smartpayment.adapters.persistence.support.OrderItemEntitySupport.defaultOrderItemEntity;
import static br.gabrielsmartins.smartpayment.application.support.OrderItemSupport.defaultOrderItem;
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
        OrderItem orderItem = defaultOrderItem().build();

        OrderItemEntity orderItemEntity = this.mapper.mapToEntity(orderItem);

        assertThat(orderItemEntity).hasNoNullFieldsOrPropertiesExcept("order");
        assertThat(orderItemEntity.getProductId()).isEqualTo(orderItem.getProductId());
        assertThat(orderItemEntity.getQuantity()).isEqualTo(orderItem.getQuantity());
        assertThat(orderItemEntity.getAmount()).isEqualTo(orderItem.getAmount());
    }

    @Test
    @DisplayName("Given Order Item Entity When Map Then Return Order Item")
    public void givenOrderItemEntityWhenMapThenReturnOrderItem(){

        OrderItemEntity orderItemEntity = defaultOrderItemEntity().build();

        OrderItem orderItem = this.mapper.mapToDomain(orderItemEntity);

        assertThat(orderItem).hasNoNullFieldsOrPropertiesExcept("order");
        assertThat(orderItem.getProductId()).isEqualTo(orderItemEntity.getProductId());
        assertThat(orderItem.getQuantity()).isEqualTo(orderItemEntity.getQuantity());
        assertThat(orderItem.getAmount()).isEqualTo(orderItemEntity.getAmount());
    }
}

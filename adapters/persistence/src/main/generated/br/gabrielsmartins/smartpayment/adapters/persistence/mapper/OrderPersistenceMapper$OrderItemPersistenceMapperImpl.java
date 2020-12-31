package br.gabrielsmartins.smartpayment.adapters.persistence.mapper;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.OrderEntity.OrderItemEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.mapper.OrderPersistenceMapper.OrderItemPersistenceMapper;
import br.gabrielsmartins.smartpayment.application.domain.Order.OrderItem;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-31T20:21:39-0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.5 (Oracle Corporation)"
)
@Component
public class OrderPersistenceMapper$OrderItemPersistenceMapperImpl implements OrderItemPersistenceMapper {

    @Override
    public OrderItemEntity mapToEntity(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }

        OrderItemEntity orderItemEntity = new OrderItemEntity();

        orderItemEntity.setProductId( orderItem.getProductId() );
        orderItemEntity.setQuantity( orderItem.getQuantity() );
        orderItemEntity.setAmount( orderItem.getAmount() );

        return orderItemEntity;
    }

    @Override
    public OrderItem mapToDomain(OrderItemEntity orderItemEntity) {
        if ( orderItemEntity == null ) {
            return null;
        }

        OrderItem orderItem = new OrderItem();

        orderItem.setProductId( orderItemEntity.getProductId() );
        orderItem.setQuantity( orderItemEntity.getQuantity() );
        orderItem.setAmount( orderItemEntity.getAmount() );

        return orderItem;
    }
}

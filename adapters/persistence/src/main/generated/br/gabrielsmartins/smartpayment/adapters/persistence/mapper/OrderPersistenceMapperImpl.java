package br.gabrielsmartins.smartpayment.adapters.persistence.mapper;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.OrderEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.entity.OrderEntity.OrderItemEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.mapper.OrderPersistenceMapper.OrderItemPersistenceMapper;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.Order.OrderItem;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-31T20:21:39-0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.5 (Oracle Corporation)"
)
@Component
public class OrderPersistenceMapperImpl implements OrderPersistenceMapper {

    private final OrderItemPersistenceMapper orderItemPersistenceMapper;

    @Autowired
    public OrderPersistenceMapperImpl(OrderItemPersistenceMapper orderItemPersistenceMapper) {

        this.orderItemPersistenceMapper = orderItemPersistenceMapper;
    }

    @Override
    public OrderEntity mapToEntity(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setId( order.getId() );
        orderEntity.setCustomerId( order.getCustomerId() );
        orderEntity.setCreatedAt( order.getCreatedAt() );
        orderEntity.setFinishedAt( order.getFinishedAt() );
        orderEntity.setTotalAmount( order.getTotalAmount() );
        orderEntity.setTotalDiscount( order.getTotalDiscount() );
        orderEntity.setStatus( order.getStatus() );
        orderEntity.setState( order.getState() );
        if ( order.getItems() != null ) {
            for ( OrderItem item : order.getItems() ) {
                orderEntity.addItem( orderItemPersistenceMapper.mapToEntity( item ) );
            }
        }

        addCollections( order, orderEntity );

        return orderEntity;
    }

    @Override
    public Order mapToDomain(OrderEntity orderEntity) {
        if ( orderEntity == null ) {
            return null;
        }

        Order order = new Order();

        order.setId( orderEntity.getId() );
        order.setCustomerId( orderEntity.getCustomerId() );
        order.setCreatedAt( orderEntity.getCreatedAt() );
        order.setFinishedAt( orderEntity.getFinishedAt() );
        order.setTotalAmount( orderEntity.getTotalAmount() );
        order.setTotalDiscount( orderEntity.getTotalDiscount() );
        order.setStatus( orderEntity.getStatus() );
        order.setState( orderEntity.getState() );
        if ( orderEntity.getItems() != null ) {
            for ( OrderItemEntity item : orderEntity.getItems() ) {
                order.addItem( orderItemPersistenceMapper.mapToDomain( item ) );
            }
        }

        addCollections( orderEntity, order );

        return order;
    }
}

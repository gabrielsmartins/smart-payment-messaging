package br.gabrielsmartins.smartpayment.adapters.persistence.mapper;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.OrderEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.entity.OrderEntity.OrderItemEntity;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.Order.OrderItem;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {OrderPersistenceMapper.OrderItemPersistenceMapper.class},
        builder = @Builder(disableBuilder = true),
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE)
public interface OrderPersistenceMapper {

    @Mappings({
            @Mapping(target = "logs", ignore = true),
            @Mapping(target = "paymentMethods", ignore = true)
    })
    OrderEntity mapToEntity(Order order);

    @Mappings({
            @Mapping(target = "logs", ignore = true),
            @Mapping(target = "paymentMethods", ignore = true)
    })
    Order mapToDomain(OrderEntity orderEntity);

    @AfterMapping
    default void addCollections(Order order, @MappingTarget OrderEntity orderEntity){
        order.getLogs().forEach(orderEntity::addLog);
        order.getPaymentMethods().forEach(orderEntity::addPaymentMethod);
    }

    @AfterMapping
    default void addCollections(OrderEntity orderEntity, @MappingTarget Order order){
        orderEntity.getLogs().forEach(order::addLog);
        orderEntity.getPaymentMethods().forEach(order::addPaymentMethod);
    }

    @Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
            unmappedSourcePolicy = ReportingPolicy.IGNORE,
            unmappedTargetPolicy = ReportingPolicy.IGNORE,
            builder = @Builder(disableBuilder = true))
    interface OrderItemPersistenceMapper{

        @Mappings({
                @Mapping(target = "order", ignore = true),
        })
        OrderItemEntity mapToEntity(OrderItem orderItem);

        @Mappings({
                @Mapping(target = "order", ignore = true),
        })@InheritInverseConfiguration
        OrderItem mapToDomain(OrderItemEntity orderItemEntity);

    }

}

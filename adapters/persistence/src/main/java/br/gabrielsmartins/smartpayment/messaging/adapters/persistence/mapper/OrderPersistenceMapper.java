package br.gabrielsmartins.smartpayment.messaging.adapters.persistence.mapper;

import br.gabrielsmartins.smartpayment.messaging.adapters.persistence.entity.OrderEntity;
import br.gabrielsmartins.smartpayment.messaging.application.domain.orders.Order;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface OrderPersistenceMapper {

    OrderEntity mapToEntity(Order order);

    Order mapToDomain(OrderEntity orderEntity);

}

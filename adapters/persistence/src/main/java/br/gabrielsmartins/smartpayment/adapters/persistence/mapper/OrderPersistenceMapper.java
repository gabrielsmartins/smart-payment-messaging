package br.gabrielsmartins.smartpayment.adapters.persistence.mapper;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.OrderEntity;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {OrderLogPersistenceMapper.class,
                OrderItemPersistenceMapper.class,
                PaymentMethodPersistenceMapper.class},
        builder = @Builder(disableBuilder = true),
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE)
public interface OrderPersistenceMapper {

    OrderEntity mapToEntity(Order order);

    Order mapToDomain(OrderEntity orderEntity);


}

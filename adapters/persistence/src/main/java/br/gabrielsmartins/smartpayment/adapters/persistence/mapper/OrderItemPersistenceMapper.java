package br.gabrielsmartins.smartpayment.adapters.persistence.mapper;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.OrderItemEntity;
import br.gabrielsmartins.smartpayment.application.domain.OrderItem;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        builder = @Builder(disableBuilder = true))
public interface OrderItemPersistenceMapper{

    @Mappings({
            @Mapping(target = "order", ignore = true),
    })
    OrderItemEntity mapToEntity(OrderItem orderItem);

    @Mappings({
            @Mapping(target = "order", ignore = true),
    })
    @InheritInverseConfiguration
    OrderItem mapToDomain(OrderItemEntity orderItemEntity);

}
package br.gabrielsmartins.smartpayment.adapters.persistence.mapper;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.OrderLogEntity;
import br.gabrielsmartins.smartpayment.application.domain.state.OrderLog;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        builder = @Builder(disableBuilder = true))
public interface OrderLogPersistenceMapper{

    @Mappings({
            @Mapping(target = "order", ignore = true),
    })
    OrderLogEntity mapToEntity(OrderLog orderLog);

    @Mappings({
            @Mapping(target = "order", ignore = true),
    })
    @InheritInverseConfiguration
    OrderLog mapToDomain(OrderLogEntity orderLogEntity);

}

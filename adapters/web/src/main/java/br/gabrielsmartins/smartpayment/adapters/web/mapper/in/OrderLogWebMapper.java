package br.gabrielsmartins.smartpayment.adapters.web.mapper.in;

import br.gabrielsmartins.smartpayment.adapters.web.adapter.in.dto.OrderLogDTO;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import br.gabrielsmartins.smartpayment.application.domain.state.OrderLog;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        builder = @Builder(disableBuilder = true))
public interface OrderLogWebMapper {

    @Mappings({
            @Mapping(target = "status", qualifiedByName = "toType")
    })
    OrderLogDTO mapToDto(OrderLog orderLog);

    @Mappings({
            @Mapping(target = "order", ignore = true),
    })
    @InheritInverseConfiguration
    OrderLog mapToDomain(OrderLogDTO orderLogDTO);

    default String toType(OrderStatus orderStatus){
        return orderStatus.getDescription();
    }

}

package br.gabrielsmartins.smartpayment.adapters.web.mapper.in;

import br.gabrielsmartins.smartpayment.adapters.web.adapter.in.dto.OrderItemDTO;
import br.gabrielsmartins.smartpayment.application.domain.OrderItem;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        builder = @Builder(disableBuilder = true))
public interface OrderItemWebMapper {

    OrderItemDTO mapToDto(OrderItem orderItem);

    @Mappings({
            @Mapping(target = "order", ignore = true),
    })
    @InheritInverseConfiguration
    OrderItem mapToDomain(OrderItemDTO orderItemDTO);

}
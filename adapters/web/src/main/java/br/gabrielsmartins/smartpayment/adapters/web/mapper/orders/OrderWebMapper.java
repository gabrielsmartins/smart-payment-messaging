package br.gabrielsmartins.smartpayment.adapters.web.mapper.orders;

import br.gabrielsmartins.smartpayment.adapters.web.dto.orders.OrderDTO;
import br.gabrielsmartins.smartpayment.domain.orders.Order;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        builder = @Builder(disableBuilder = true))
public interface OrderWebMapper {

    Order mapToDomain(OrderDTO orderDTO);

    OrderDTO mapToDto(Order order);

}

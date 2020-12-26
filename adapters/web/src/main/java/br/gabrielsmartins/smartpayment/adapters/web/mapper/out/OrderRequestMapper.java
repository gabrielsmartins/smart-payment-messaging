package br.gabrielsmartins.smartpayment.adapters.web.mapper.out;

import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import br.gabrielsmartins.smartpayment.adapters.web.adapter.out.dto.OrderRequestDTO;
import br.gabrielsmartins.smartpayment.application.domain.Order;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
unmappedSourcePolicy = ReportingPolicy.IGNORE,
unmappedTargetPolicy = ReportingPolicy.IGNORE,
builder = @Builder(disableBuilder = true))
public interface OrderRequestMapper {

	OrderRequestDTO mapToDto(Order order);
}

package br.gabrielsmartins.smartpayment.adapters.web.mapper.out;

import br.gabrielsmartins.smartpayment.adapters.web.adapter.out.dto.NotificationOrderItemDTO;
import br.gabrielsmartins.smartpayment.application.domain.OrderItem;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
unmappedSourcePolicy = ReportingPolicy.IGNORE,
unmappedTargetPolicy = ReportingPolicy.IGNORE,
builder = @Builder(disableBuilder = true))
public interface NotificationOrderItemWebMapper {

	NotificationOrderItemDTO mapToDto(OrderItem orderItem);
}

package br.gabrielsmartins.smartpayment.adapters.web.mapper.out;

import br.gabrielsmartins.smartpayment.adapters.web.adapter.out.dto.NotificationOrderDTO;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
unmappedSourcePolicy = ReportingPolicy.IGNORE,
unmappedTargetPolicy = ReportingPolicy.IGNORE,
uses = {NotificationOrderItemWebMapper.class,
		NotificationPaymentMethodWebMapper.class},
		collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
builder = @Builder(disableBuilder = true))
public interface NotificationOrderWebMapper {

	NotificationOrderDTO mapToDto(Order order);
}

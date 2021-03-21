package br.gabrielsmartins.smartpayment.adapters.web.mapper.in;

import br.gabrielsmartins.smartpayment.adapters.web.adapter.in.dto.OrderDTO;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {OrderLogWebMapper.class,
                OrderItemWebMapper.class,
                PaymentMethodWebMapper.class},
        builder = @Builder(disableBuilder = true),
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE)
public interface OrderWebMapper {

    Order mapToDomain(OrderDTO orderDTO);

    @InheritInverseConfiguration
    OrderDTO mapToDto(Order order);



}

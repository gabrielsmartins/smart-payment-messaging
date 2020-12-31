package br.gabrielsmartins.smartpayment.adapters.web.mapper.in;

import br.gabrielsmartins.smartpayment.adapters.web.adapter.in.dto.OrderDTO;
import br.gabrielsmartins.smartpayment.adapters.web.adapter.in.dto.OrderDTO.OrderItemDTO;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.Order.OrderItem;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import br.gabrielsmartins.smartpayment.application.domain.enums.PaymentType;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {OrderWebMapper.OrderItemWebMapper.class},
        builder = @Builder(disableBuilder = true),
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE)
public interface OrderWebMapper {

    @Mappings({
            @Mapping(target = "logs", ignore = true),
            @Mapping(target = "paymentMethods", ignore = true)
    })
    Order mapToDomain(OrderDTO orderDTO);

    @Mappings({
            @Mapping(target = "logs", ignore = true),
            @Mapping(target = "paymentMethods", ignore = true)
    })
    OrderDTO mapToDto(Order order);

    @AfterMapping
    default void addCollections(Order order, @MappingTarget OrderDTO orderDTO){
        order.getLogs().forEach( (k,v) -> orderDTO.addLog(k, v.getDescription()));
        order.getPaymentMethods().forEach((k,v) -> orderDTO.addPaymentMethod(k.getDescription(), v));
    }

    @AfterMapping
    default void addCollections(OrderDTO orderDTO, @MappingTarget Order order){
        orderDTO.getLogs().forEach((k,v) -> order.addLog(k, OrderStatus.valueOf(v)));
        orderDTO.getPaymentMethods().forEach((k,v) -> order.addPaymentMethod(PaymentType.valueOf(k), v));
    }


    @Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
            unmappedSourcePolicy = ReportingPolicy.IGNORE,
            unmappedTargetPolicy = ReportingPolicy.IGNORE,
            builder = @Builder(disableBuilder = true))
    interface OrderItemWebMapper{

        @Mappings({
                @Mapping(target = "order", ignore = true),
        })
        OrderItem mapToDomain(OrderItemDTO orderItemDTO);

        OrderItemDTO mapToDto(OrderItem orderItem);
    }

}

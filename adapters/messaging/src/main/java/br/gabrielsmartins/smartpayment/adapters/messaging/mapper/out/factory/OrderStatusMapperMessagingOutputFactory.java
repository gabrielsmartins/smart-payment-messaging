package br.gabrielsmartins.smartpayment.adapters.messaging.mapper.out.factory;

import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class OrderStatusMapperMessagingOutputFactory {

    private static final Map<OrderStatus, OrderStatusMessagingOutputMapper<?>> MAPPERS_MAP = new LinkedHashMap<>();

    public OrderStatusMapperMessagingOutputFactory(List<OrderStatusMessagingOutputMapper<?>> mappers){
        MAPPERS_MAP.putAll(build(mappers));
    }

    public OrderStatusMessagingOutputMapper<?> createMapper(OrderStatus orderStatus){
        return MAPPERS_MAP.get(orderStatus);
    }

    private Map<OrderStatus, OrderStatusMessagingOutputMapper<?>> build(List<OrderStatusMessagingOutputMapper<?>> mappers){
        return mappers.stream()
                      .collect(Collectors.toMap(OrderStatusMessagingOutputMapper::getOrderStatus, Function.identity()));
    }

}

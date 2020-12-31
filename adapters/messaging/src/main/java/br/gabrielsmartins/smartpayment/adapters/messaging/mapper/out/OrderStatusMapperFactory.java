package br.gabrielsmartins.smartpayment.adapters.messaging.mapper.out;

import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class OrderStatusMapperFactory {

    private static final Map<OrderStatus, OrderStatusMapper<?>> MAPPERS_MAP = new LinkedHashMap<>();

    public OrderStatusMapperFactory(List<OrderStatusMapper<?>> mappers){
        MAPPERS_MAP.putAll(build(mappers));
    }

    public OrderStatusMapper<?> createMapper(OrderStatus orderStatus){
        return MAPPERS_MAP.get(orderStatus);
    }

    private Map<OrderStatus, OrderStatusMapper<?>> build(List<OrderStatusMapper<?>> mappers){
        return mappers.stream()
                      .collect(Collectors.toMap(OrderStatusMapper::getOrderStatus, Function.identity()));
    }

}

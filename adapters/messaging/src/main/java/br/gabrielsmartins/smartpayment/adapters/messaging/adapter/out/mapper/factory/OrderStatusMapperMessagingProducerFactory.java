package br.gabrielsmartins.smartpayment.adapters.messaging.adapter.out.mapper.factory;

import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class OrderStatusMapperMessagingProducerFactory {

    private static final Map<OrderStatus, OrderStatusMessagingProducerMapper<?>> MAPPERS_MAP = new LinkedHashMap<>();

    public OrderStatusMapperMessagingProducerFactory(List<OrderStatusMessagingProducerMapper<?>> mappers){
        MAPPERS_MAP.putAll(build(mappers));
    }

    public OrderStatusMessagingProducerMapper<?> createMapper(OrderStatus orderStatus){
        return MAPPERS_MAP.get(orderStatus);
    }

    private Map<OrderStatus, OrderStatusMessagingProducerMapper<?>> build(List<OrderStatusMessagingProducerMapper<?>> mappers){
        return mappers.stream()
                      .collect(Collectors.toMap(OrderStatusMessagingProducerMapper::getOrderStatus, Function.identity()));
    }

}

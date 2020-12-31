package br.gabrielsmartins.smartpayment.adapters.messaging.mapper.out;

import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class OrderStatusMapperFactory {

    private static OrderStatusMapperFactory factory;
    private static final Map<String, OrderStatusMapper<?>> MAPPERS_MAP = new LinkedHashMap<>();
    private static List<OrderStatusMapper<?>> mappers;

    @Autowired
    private OrderStatusMapperFactory(List<OrderStatusMapper<?>> mappers){
        OrderStatusMapperFactory.mappers = mappers;
        MAPPERS_MAP.putAll(build(mappers));
    }

    public static OrderStatusMapper<?> createMapper(OrderStatus orderStatus){
        return MAPPERS_MAP.get(orderStatus);
    }

    private Map<String, OrderStatusMapper<?>> build(List<OrderStatusMapper<?>> mappers){
        return mappers.stream()
                      .collect(Collectors.toMap(this::getKey, Function.identity()));
    }

    private String getKey(OrderStatusMapper<?> mapper){
        Class<?> clazz = (Class<?>) ((ParameterizedType) (mapper.getClass().getGenericSuperclass())).getActualTypeArguments()[0];
        return clazz.getName();
    }

    public static OrderStatusMapperFactory getInstance(){
        if(factory == null) {
            factory = new OrderStatusMapperFactory(mappers);
        }
        return factory;
    }

}

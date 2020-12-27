package br.gabrielsmartins.smartpayment.adapters.messaging.mapper.out;

import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class OrderStatusMapperFactory {

    private static OrderStatusMapperFactory factory;
    private static final List<OrderStatusMapper<?>> MAPPERS = new LinkedList<>();

    @Autowired
    private OrderStatusMapperFactory(List<OrderStatusMapper<?>> mappers){
        MAPPERS.addAll(mappers);
    }

    public static OrderStatusMapper<?> createMapper(OrderStatus status){
        Class<? extends OrderStatusMapper> clazz = extractMapperClass(status);
        return MAPPERS.stream()
                      .filter(m -> m.getClass().isAssignableFrom(clazz))
                      .findFirst()
                      .orElseThrow();
    }

    private static Class<? extends OrderStatusMapper> extractMapperClass(OrderStatus status) {
        switch (status){
            case REQUESTED:
                return RequestedOrderStatusMapper.class;

            case VALIDATED:
                return ValidatedOrderStatusMapper.class;

            case REJECTED:
                return RejectedOrderStatusMapper.class;

            case CONFIRMED:
                return ConfirmedOrderStatusMapper.class;

            case COMPLETED:
                return CompletedOrderStatusMapper.class;
        }
        return null;
    }

    public static OrderStatusMapperFactory getInstance(){
        if(factory == null) {
            factory = new OrderStatusMapperFactory(MAPPERS);
        }
        return factory;
    }

}

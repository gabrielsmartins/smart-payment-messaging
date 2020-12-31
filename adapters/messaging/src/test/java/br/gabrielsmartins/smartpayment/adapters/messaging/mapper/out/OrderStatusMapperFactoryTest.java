package br.gabrielsmartins.smartpayment.adapters.messaging.mapper.out;

import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderStatusMapperFactoryTest {

    private OrderStatusMapperFactory factory;

    @BeforeEach
    public void setup() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<OrderStatusMapperFactory> clazz = OrderStatusMapperFactory.class;
        Constructor<OrderStatusMapperFactory> constructor = clazz.getDeclaredConstructor(List.class);
        constructor.setAccessible(true);

        CompletedOrderItemStatusMapper mapper = new CompletedOrderItemStatusMapper();
        this.factory = constructor.newInstance(List.of(mapper));
    }

    @Test
    @DisplayName("Given Method When Is Called Then Return Instance")
    public void givenMethodWhenIsCalledThenReturnInstance(){
        OrderStatusMapperFactory instance = OrderStatusMapperFactory.getInstance();

        assertThat(instance).isNotNull();
    }

    @Test
    @DisplayName("Given Status When Exists Then Return Mapper")
    public void givenStatusWhenExistsThenReturnMapper(){
        OrderStatusMapper<?> mapper = OrderStatusMapperFactory.createMapper(OrderStatus.COMPLETED);

        assertThat(mapper).isNotNull();
        assertThat(mapper).isInstanceOf(CompletedOrderItemStatusMapper.class);
    }


}

package br.gabrielsmartins.smartpayment.adapters.messaging.mapper.out;

import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderStatusMapperFactoryTest {

    private OrderStatusMapperFactory factory;

    @BeforeEach
    public void setup() {
        CompletedOrderItemStatusMapper itemStatusMapper = new CompletedOrderItemStatusMapper();
        CompletedOrderStatusMapper mapper = new CompletedOrderStatusMapper(itemStatusMapper);
        this.factory = new OrderStatusMapperFactory(List.of(mapper));
    }


    @Test
    @DisplayName("Given Status When Exists Then Return Mapper")
    public void givenStatusWhenExistsThenReturnMapper(){
        OrderStatusMapper<?> mapper = factory.createMapper(OrderStatus.COMPLETED);

        assertThat(mapper).isNotNull();
        assertThat(mapper).isInstanceOf(CompletedOrderStatusMapper.class);
    }


}

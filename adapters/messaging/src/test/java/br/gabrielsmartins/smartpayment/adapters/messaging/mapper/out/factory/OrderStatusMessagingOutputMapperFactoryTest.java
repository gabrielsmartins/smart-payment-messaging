package br.gabrielsmartins.smartpayment.adapters.messaging.mapper.out.factory;

import br.gabrielsmartins.smartpayment.adapters.messaging.mapper.out.completed.CompletedOrderItemStatusMessagingOutputMapper;
import br.gabrielsmartins.smartpayment.adapters.messaging.mapper.out.completed.CompletedOrderStatusMessagingOutputMapper;
import br.gabrielsmartins.smartpayment.adapters.messaging.mapper.out.completed.CompletedOrderStatusPaymentMethodMessagingOutputMapper;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderStatusMessagingOutputMapperFactoryTest {

    private OrderStatusMapperMessagingOutputFactory factory;

    @BeforeEach
    public void setup() {
        var itemStatusMapper = new CompletedOrderItemStatusMessagingOutputMapper();
        var paymentMethodMapper = new CompletedOrderStatusPaymentMethodMessagingOutputMapper();
        CompletedOrderStatusMessagingOutputMapper mapper = new CompletedOrderStatusMessagingOutputMapper(itemStatusMapper, paymentMethodMapper);
        this.factory = new OrderStatusMapperMessagingOutputFactory(List.of(mapper));
    }


    @Test
    @DisplayName("Given Status When Exists Then Return Mapper")
    public void givenStatusWhenExistsThenReturnMapper(){
        OrderStatusMessagingOutputMapper<?> mapper = factory.createMapper(OrderStatus.COMPLETED);

        assertThat(mapper).isNotNull();
        assertThat(mapper).isInstanceOf(CompletedOrderStatusMessagingOutputMapper.class);
    }


}

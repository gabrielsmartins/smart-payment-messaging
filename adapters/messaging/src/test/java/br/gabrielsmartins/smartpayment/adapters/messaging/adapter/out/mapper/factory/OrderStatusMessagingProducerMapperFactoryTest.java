package br.gabrielsmartins.smartpayment.adapters.messaging.adapter.out.mapper.factory;

import br.gabrielsmartins.smartpayment.adapters.messaging.adapter.out.mapper.completed.CompletedOrderItemStatusMessagingProducerMapper;
import br.gabrielsmartins.smartpayment.adapters.messaging.adapter.out.mapper.completed.CompletedOrderStatusMessagingProducerMapper;
import br.gabrielsmartins.smartpayment.adapters.messaging.adapter.out.mapper.completed.CompletedOrderStatusPaymentMethodMessagingProducerMapper;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderStatusMessagingProducerMapperFactoryTest {

    private OrderStatusMapperMessagingProducerFactory factory;

    @BeforeEach
    public void setup() {
        var itemStatusMapper = new CompletedOrderItemStatusMessagingProducerMapper();
        var paymentMethodMapper = new CompletedOrderStatusPaymentMethodMessagingProducerMapper();
        CompletedOrderStatusMessagingProducerMapper mapper = new CompletedOrderStatusMessagingProducerMapper(itemStatusMapper, paymentMethodMapper);
        this.factory = new OrderStatusMapperMessagingProducerFactory(List.of(mapper));
    }


    @Test
    @DisplayName("Given Status When Exists Then Return Mapper")
    public void givenStatusWhenExistsThenReturnMapper(){
        OrderStatusMessagingProducerMapper<?> mapper = factory.createMapper(OrderStatus.COMPLETED);

        assertThat(mapper).isNotNull();
        assertThat(mapper).isInstanceOf(CompletedOrderStatusMessagingProducerMapper.class);
    }


}

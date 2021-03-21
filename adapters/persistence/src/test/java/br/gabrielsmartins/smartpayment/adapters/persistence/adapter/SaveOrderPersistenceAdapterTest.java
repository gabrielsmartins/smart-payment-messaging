package br.gabrielsmartins.smartpayment.adapters.persistence.adapter;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.OrderEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.mapper.*;
import br.gabrielsmartins.smartpayment.adapters.persistence.service.SaveOrderPersistenceService;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.gabrielsmartins.smartpayment.application.support.OrderSupport.defaultOrder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SaveOrderPersistenceAdapterTest {

    private SaveOrderPersistenceAdapter adapter;
    private SaveOrderPersistenceService service;
    private OrderPersistenceMapper mapper;

    @BeforeEach
    public void setup(){
        this.service = mock(SaveOrderPersistenceService.class);
        OrderLogPersistenceMapper orderLogPersistenceMapper = new OrderLogPersistenceMapperImpl();
        OrderItemPersistenceMapper itemPersistenceMapper = new OrderItemPersistenceMapperImpl();
        PaymentMethodPersistenceMapper paymentMethodPersistenceMapper = new PaymentMethodPersistenceMapperImpl();
        this.mapper = new OrderPersistenceMapperImpl(orderLogPersistenceMapper,itemPersistenceMapper, paymentMethodPersistenceMapper);
        this.adapter = new SaveOrderPersistenceAdapter(service,mapper);
    }

    @Test
    @DisplayName("Given Order When Save Then Return Saved Order")
    public void givenOrderWhenSaveThenReturnSavedOrder(){

        Order order = defaultOrder().build();

        when(service.save(any(OrderEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Order savedOrder = adapter.save(order);
        assertThat(savedOrder).isNotNull();
    }

}

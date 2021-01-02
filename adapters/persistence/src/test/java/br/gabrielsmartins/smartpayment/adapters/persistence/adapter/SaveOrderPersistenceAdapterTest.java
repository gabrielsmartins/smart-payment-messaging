package br.gabrielsmartins.smartpayment.adapters.persistence.adapter;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.OrderEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.mapper.*;
import br.gabrielsmartins.smartpayment.adapters.persistence.service.SaveOrderPersistenceService;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

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

        Order order = Order.builder()
                            .withId(UUID.randomUUID().toString())
                            .withCustomerId(UUID.randomUUID())
                            .withCreatedAt(LocalDateTime.now())
                            .withFinishedAt(LocalDateTime.now())
                            .withStatus(OrderStatus.COMPLETED)
                            .withTotalAmount(BigDecimal.valueOf(1500.00))
                            .withTotalDiscount(BigDecimal.valueOf(1400.00))
                            .build();


        when(service.save(any(OrderEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Order savedOrder = adapter.save(order);
        assertThat(savedOrder).isNotNull();
    }

}

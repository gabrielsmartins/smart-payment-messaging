package br.gabrielsmartins.smartpayment.adapters.persistence.adapter;

import br.gabrielsmartins.smartpayment.adapters.persistence.mapper.*;
import br.gabrielsmartins.smartpayment.adapters.persistence.service.SearchOrderPersistenceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static br.gabrielsmartins.smartpayment.adapters.persistence.support.OrderEntitySupport.defaultOrderEntity;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SearchOrderPersistenceAdapterTest {

    private SearchOrderPersistenceAdapter adapter;
    private SearchOrderPersistenceService service;
    private OrderPersistenceMapper mapper;

    @BeforeEach
    public void setup(){
        this.service = mock(SearchOrderPersistenceService.class);
        OrderLogPersistenceMapper orderLogPersistenceMapper = new OrderLogPersistenceMapperImpl();
        OrderItemPersistenceMapper itemPersistenceMapper = new OrderItemPersistenceMapperImpl();
        PaymentMethodPersistenceMapper paymentMethodPersistenceMapper = new PaymentMethodPersistenceMapperImpl();
        this.mapper = new OrderPersistenceMapperImpl(orderLogPersistenceMapper,itemPersistenceMapper, paymentMethodPersistenceMapper);
        this.adapter = new SearchOrderPersistenceAdapter(service,mapper);
    }

    @Test
    @DisplayName("Given Order Id When Exists Then Return Order")
    public void givenOrderIdWhenExistsThenReturnOrder(){

        when(this.service.findById(anyLong())).thenReturn(Mono.just(defaultOrderEntity().build()));

        this.adapter.findById(1245L)
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();
    }
}

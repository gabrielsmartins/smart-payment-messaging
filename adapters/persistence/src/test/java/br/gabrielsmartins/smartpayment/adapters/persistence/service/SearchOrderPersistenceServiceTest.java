package br.gabrielsmartins.smartpayment.adapters.persistence.service;

import br.gabrielsmartins.smartpayment.adapters.persistence.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static br.gabrielsmartins.smartpayment.adapters.persistence.support.OrderEntitySupport.defaultOrderEntity;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SearchOrderPersistenceServiceTest {

    private SearchOrderPersistenceService service;
    private OrderRepository repository;

    @BeforeEach
    public void setup(){
        this.repository = mock(OrderRepository.class);
       this.service = new SearchOrderPersistenceService(repository);
    }

    @Test
    @DisplayName("Given Order Id When Exists Then Return Order")
    public void givenOrderIdWhenExistsThenReturnOrder(){

        when(this.repository.findById(anyLong())).thenReturn(Mono.just(defaultOrderEntity().build()));

        this.service.findById(1245L)
                    .as(StepVerifier::create)
                    .expectNextCount(1)
                    .verifyComplete();
    }
}

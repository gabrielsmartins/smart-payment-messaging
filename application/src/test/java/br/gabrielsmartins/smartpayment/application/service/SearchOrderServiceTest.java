package br.gabrielsmartins.smartpayment.application.service;

import br.gabrielsmartins.smartpayment.application.ports.out.SearchOrderPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static br.gabrielsmartins.smartpayment.application.support.OrderSupport.defaultOrder;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class SearchOrderServiceTest {

    private SearchOrderService service;
    private SearchOrderPort port;


    @BeforeEach
    public void setup(){
        this.port = mock(SearchOrderPort.class);
        this.service = new SearchOrderService(port);
    }

    @Test
    @DisplayName("Given Order Id When Exists Then Return Order")
    public void givenOrderIdWhenExistsThenReturnOrder(){

        when(this.port.findById(anyLong())).thenReturn(Mono.just(defaultOrder().build()));

        this.service.findById(12345L)
                    .as(StepVerifier::create)
                    .expectNextCount(1)
                    .verifyComplete();
    }
}

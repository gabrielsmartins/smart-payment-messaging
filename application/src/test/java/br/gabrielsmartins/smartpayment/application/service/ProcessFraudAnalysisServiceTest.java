package br.gabrielsmartins.smartpayment.application.service;

import br.gabrielsmartins.smartpayment.application.ports.in.SearchOrderUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static br.gabrielsmartins.smartpayment.application.support.OrderSupport.defaultOrder;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProcessFraudAnalysisServiceTest {

    private ProcessFraudAnalysisService service;
    private SearchOrderUseCase searchOrderUseCase;

    @BeforeEach
    public void setup(){
        this.searchOrderUseCase = mock(SearchOrderUseCase.class);
        this.service = new ProcessFraudAnalysisService(searchOrderUseCase);
    }

    @Test
    @DisplayName("Given Order Id When Exists Then Return Order")
    public void givenOrderIdWhenExistsThenReturnOrder(){

        when(searchOrderUseCase.findById(anyLong())).thenReturn(Mono.just(defaultOrder().build()));

        Long orderId = 12345L;
        this.service.process(orderId, true)
                    .as(StepVerifier::create)
                    .expectNextCount(1)
                    .verifyComplete();

    }

    @Test
    @DisplayName("Given Order Id When Not Exists Then Return Empty")
    public void givenOrderIdWhenNotExistsThenReturnEmpty(){

        when(searchOrderUseCase.findById(anyLong())).thenReturn(Mono.empty());

        Long orderId = 12345L;
        this.service.process(orderId, true)
                .as(StepVerifier::create)
                .expectNextCount(0)
                .verifyComplete();

    }
}

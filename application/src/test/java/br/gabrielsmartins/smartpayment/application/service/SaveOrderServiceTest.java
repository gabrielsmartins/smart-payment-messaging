package br.gabrielsmartins.smartpayment.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.ports.out.SaveOrderPort;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class SaveOrderServiceTest {

    private SaveOrderService service;
    private SaveOrderPort port;

    @BeforeEach
    public void setup(){
        this.port = mock(SaveOrderPort.class);
        this.service = new SaveOrderService(port);
    }

    @Test
    @DisplayName("Given Order When Save Then Return Saved Order")
    public void givenOrderWhenSaveThenReturnSavedOrder(){

        when(port.save(any(Order.class))).thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        service.save(new Order())
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();

        verify(port, times(1)).save(any(Order.class));
    }
}

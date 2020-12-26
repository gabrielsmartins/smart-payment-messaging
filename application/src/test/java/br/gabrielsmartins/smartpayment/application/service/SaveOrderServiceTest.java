package br.gabrielsmartins.smartpayment.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.ports.out.SaveOrderPort;

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

        when(port.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Order savedOrder = service.save(new Order());
        assertThat(savedOrder).isNotNull();
    }
}

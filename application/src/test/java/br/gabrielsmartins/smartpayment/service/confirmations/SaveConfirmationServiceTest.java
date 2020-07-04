package br.gabrielsmartins.smartpayment.service.confirmations;


import br.gabrielsmartins.smartpayment.domain.confirmations.Confirmation;
import br.gabrielsmartins.smartpayment.domain.enums.ConfirmationStatus;
import br.gabrielsmartins.smartpayment.ports.out.confirmations.SaveConfirmationPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SaveConfirmationServiceTest {

    private SaveConfirmationService service;
    private SaveConfirmationPort port;

    @BeforeEach
    public void setup(){
        this.port = mock(SaveConfirmationPort.class);
        this.service = new SaveConfirmationService(port);
    }

    @Test
    @DisplayName("Given Confirmation When Save Then Return Saved Confirmation")
    public void givenConfirmationWhenSaveThenReturnSavedConfirmation(){

        Confirmation confirmation = Confirmation.builder()
                                                 .withId(UUID.randomUUID().toString())
                                                 .withDateTime(LocalDateTime.now())
                                                 .withStatus(ConfirmationStatus.ACCEPTED)
                                                 .withPaymentId(UUID.randomUUID().toString())
                                                 .build();

        when(port.save(any(Confirmation.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Confirmation savedConfirmation = service.save(confirmation);
        assertThat(savedConfirmation).isNotNull();
    }
}

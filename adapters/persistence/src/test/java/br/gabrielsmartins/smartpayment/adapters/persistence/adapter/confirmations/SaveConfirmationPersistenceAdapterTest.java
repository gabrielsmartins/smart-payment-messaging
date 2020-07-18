package br.gabrielsmartins.smartpayment.adapters.persistence.adapter.confirmations;


import br.gabrielsmartins.smartpayment.adapters.persistence.entity.confirmations.ConfirmationEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.mapper.confirmations.ConfirmationPersistenceMapper;
import br.gabrielsmartins.smartpayment.adapters.persistence.mapper.confirmations.ConfirmationPersistenceMapperImpl;
import br.gabrielsmartins.smartpayment.adapters.persistence.service.confirmations.SaveConfirmationPersistenceService;
import br.gabrielsmartins.smartpayment.application.domain.confirmations.Confirmation;
import br.gabrielsmartins.smartpayment.application.domain.enums.ConfirmationStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SaveConfirmationPersistenceAdapterTest {

    private SaveConfirmationPersistenceAdapter adapter;
    private SaveConfirmationPersistenceService service;
    private ConfirmationPersistenceMapper mapper;

    @BeforeEach
    public void setup(){
        this.service = mock(SaveConfirmationPersistenceService.class);
        this.mapper = new ConfirmationPersistenceMapperImpl();
        this.adapter = new SaveConfirmationPersistenceAdapter(service,mapper);
    }

    @Test
    @DisplayName("Given Confirmation When Save Then Return Saved Confirmation")
    public void givenConfirmationWhenSaveThenReturnSavedConfirmation(){

        Confirmation confirmation = Confirmation.builder()
                                        .withId(UUID.randomUUID().toString())
                                        .withDateTime(LocalDateTime.now())
                                        .withStatus(ConfirmationStatus.ACCEPTED)
                                        .withId(UUID.randomUUID().toString())
                                        .build();

        when(service.save(any(ConfirmationEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Confirmation savedConfirmation = adapter.save(confirmation);
        assertThat(savedConfirmation).isNotNull();
    }

}

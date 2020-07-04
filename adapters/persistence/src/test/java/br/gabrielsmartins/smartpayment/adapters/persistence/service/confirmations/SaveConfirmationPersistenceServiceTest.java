package br.gabrielsmartins.smartpayment.adapters.persistence.service.confirmations;


import br.gabrielsmartins.smartpayment.adapters.persistence.entity.confirmations.ConfirmationEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.repository.confirmations.ConfirmationRepository;
import br.gabrielsmartins.smartpayment.domain.enums.ConfirmationStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SaveConfirmationPersistenceServiceTest {

    private SaveConfirmationPersistenceService service;
    private ConfirmationRepository repository;

    @BeforeEach
    public void setup(){
        this.repository = mock(ConfirmationRepository.class);
       this.service = new SaveConfirmationPersistenceService(repository);
    }

    @Test
    @DisplayName("Given Confirmation When Save Then Return Saved Confirmation")
    public void givenConfirmationWhenSaveThenReturnSavedConfirmation(){
        ConfirmationEntity confirmationEntity = ConfirmationEntity.builder()
                                            .withId(UUID.randomUUID().toString())
                                            .withDateTime(LocalDateTime.now())
                                            .withStatus(ConfirmationStatus.ACCEPTED)
                                            .withId(UUID.randomUUID().toString())
                                            .build();

        when(repository.save(any(ConfirmationEntity.class))).then(invocation -> invocation.getArgument(0));

        ConfirmationEntity savedConfirmation = this.service.save(confirmationEntity);
        assertThat(savedConfirmation).isNotNull();
    }
}

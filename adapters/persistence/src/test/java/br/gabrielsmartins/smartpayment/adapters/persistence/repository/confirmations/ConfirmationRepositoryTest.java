package br.gabrielsmartins.smartpayment.adapters.persistence.repository.confirmations;


import br.gabrielsmartins.smartpayment.adapters.persistence.PersistenceAdapterTest;
import br.gabrielsmartins.smartpayment.adapters.persistence.entity.confirmations.ConfirmationEntity;
import br.gabrielsmartins.smartpayment.application.domain.enums.ConfirmationStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {PersistenceAdapterTest.class})
@ActiveProfiles("test")
public class ConfirmationRepositoryTest {

    @Autowired
    private ConfirmationRepository repository;


    @Test
    @DisplayName("Given Confirmation When Save Then Return Saved Confirmation")
    public void givenConfirmationWhenSaveThenReturnSavedConfirmation(){
        ConfirmationEntity confirmationEntity = ConfirmationEntity.builder()
                .withId(UUID.randomUUID().toString())
                .withDateTime(LocalDateTime.now())
                .withStatus(ConfirmationStatus.ACCEPTED)
                .withId(UUID.randomUUID().toString())
                .build();


        ConfirmationEntity savedConfirmation = this.repository.save(confirmationEntity);
        assertThat(savedConfirmation).isNotNull();
    }
}

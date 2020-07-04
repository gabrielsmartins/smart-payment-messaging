package br.gabrielsmartins.smartpayment.adapters.persistence.mapper.confirmations;


import br.gabrielsmartins.smartpayment.adapters.persistence.entity.confirmations.ConfirmationEntity;
import br.gabrielsmartins.smartpayment.domain.confirmations.Confirmation;
import br.gabrielsmartins.smartpayment.domain.enums.ConfirmationStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


public class ConfirmationPersistenceMapperTest {

    private ConfirmationPersistenceMapper mapper;


    @BeforeEach
    public void setup(){
        this.mapper = new ConfirmationPersistenceMapperImpl();
    }

    @Test
    @DisplayName("Given Confirmation Entity When Map Then Return Confirmation Domain")
    public void givenConfirmationEntityWhenMapThenReturnConfirmationDomain(){

        Confirmation confirmation = Confirmation.builder()
                                                .withId(UUID.randomUUID().toString())
                                                .withDateTime(LocalDateTime.now())
                                                .withStatus(ConfirmationStatus.ACCEPTED)
                                                .withId(UUID.randomUUID().toString())
                                                .build();

        ConfirmationEntity confirmationEntity = this.mapper.mapToEntity(confirmation);

        assertThat(confirmationEntity.getId()).isEqualTo(confirmation.getId());
        assertThat(confirmationEntity.getDateTime()).isEqualTo(confirmation.getDateTime());
        assertThat(confirmationEntity.getPaymentId()).isEqualTo(confirmation.getPaymentId());
        assertThat(confirmationEntity.getStatus()).isEqualTo(confirmation.getStatus());
    }

    @Test
    @DisplayName("Given Confirmation Domain When Map Then Return Confirmation Entity")
    public void givenConfirmationDomainWhenMapThenReturnConfirmationEntity(){

        ConfirmationEntity confirmationEntity = ConfirmationEntity.builder()
                                                    .withId(UUID.randomUUID().toString())
                                                    .withDateTime(LocalDateTime.now())
                                                    .withStatus(ConfirmationStatus.ACCEPTED)
                                                    .withId(UUID.randomUUID().toString())
                                                    .build();
        
        Confirmation confirmation = this.mapper.mapToDomain(confirmationEntity);

        assertThat(confirmation.getId()).isEqualTo(confirmationEntity.getId());
        assertThat(confirmation.getDateTime()).isEqualTo(confirmationEntity.getDateTime());
        assertThat(confirmation.getPaymentId()).isEqualTo(confirmationEntity.getPaymentId());
        assertThat(confirmation.getStatus()).isEqualTo(confirmationEntity.getStatus());
    }
}

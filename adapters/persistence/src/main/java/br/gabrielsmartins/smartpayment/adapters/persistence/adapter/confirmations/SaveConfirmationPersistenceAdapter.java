package br.gabrielsmartins.smartpayment.adapters.persistence.adapter.confirmations;


import br.gabrielsmartins.smartpayment.adapters.persistence.entity.confirmations.ConfirmationEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.mapper.confirmations.ConfirmationPersistenceMapper;
import br.gabrielsmartins.smartpayment.adapters.persistence.service.confirmations.SaveConfirmationPersistenceService;
import br.gabrielsmartins.smartpayment.domain.confirmations.Confirmation;
import br.gabrielsmartins.smartpayment.ports.out.confirmations.SaveConfirmationPort;
import br.gabrielsmartins.smartpayment.common.stereotype.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class SaveConfirmationPersistenceAdapter implements SaveConfirmationPort {

    private final SaveConfirmationPersistenceService service;
    private final ConfirmationPersistenceMapper mapper;

    @Override
    public Confirmation save(Confirmation confirmation) {
        ConfirmationEntity confirmationEntity = mapper.mapToEntity(confirmation);
        ConfirmationEntity savedConfirmationEntity = service.save(confirmationEntity);
        return mapper.mapToDomain(savedConfirmationEntity);
    }
}

package br.gabrielsmartins.smartpayment.adapters.persistence.service.confirmations;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.confirmations.ConfirmationEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.repository.confirmations.ConfirmationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveConfirmationPersistenceService implements ISavePaymentPersistenceService{

    private final ConfirmationRepository repository;

    @Override
    public ConfirmationEntity save(ConfirmationEntity confirmationEntity) {
        return repository.save(confirmationEntity);
    }
}

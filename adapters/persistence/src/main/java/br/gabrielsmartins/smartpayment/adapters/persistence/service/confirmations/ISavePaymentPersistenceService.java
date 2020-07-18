package br.gabrielsmartins.smartpayment.adapters.persistence.service.confirmations;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.confirmations.ConfirmationEntity;

public interface ISavePaymentPersistenceService {

    ConfirmationEntity save(ConfirmationEntity paymentEntity);

}

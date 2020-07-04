package br.gabrielsmartins.smartpayment.messaging.adapters.persistence.service.payments;

import br.gabrielsmartins.smartpayment.messaging.adapters.persistence.entity.payments.PaymentEntity;

public interface ISavePaymentPersistenceService {

    PaymentEntity save(PaymentEntity paymentEntity);

}

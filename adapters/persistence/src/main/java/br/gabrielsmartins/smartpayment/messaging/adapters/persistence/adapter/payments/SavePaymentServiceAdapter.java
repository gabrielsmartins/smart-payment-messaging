package br.gabrielsmartins.smartpayment.messaging.adapters.persistence.adapter.payments;

import br.gabrielsmartins.smartpayment.messaging.adapters.persistence.mapper.payments.PaymentPersistenceMapper;
import br.gabrielsmartins.smartpayment.messaging.adapters.persistence.service.payments.SavePaymentPersistenceService;
import br.gabrielsmartins.smartpayment.messaging.application.domain.payments.Payment;
import br.gabrielsmartins.smartpayment.messaging.application.ports.out.payments.SavePaymentPort;
import br.gabrielsmartins.smartpayment.messaging.common.stereotype.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class SavePaymentServiceAdapter implements SavePaymentPort {

    private final SavePaymentPersistenceService service;
    private final PaymentPersistenceMapper mapper;


    @Override
    public Payment save(Payment payment) {

        return null;
    }
}

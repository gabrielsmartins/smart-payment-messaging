package br.gabrielsmartins.smartpayment.messaging.adapters.persistence.service.payments;

import br.gabrielsmartins.smartpayment.messaging.adapters.persistence.entity.payments.PaymentEntity;
import br.gabrielsmartins.smartpayment.messaging.adapters.persistence.repository.payments.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SavePaymentPersistenceService implements ISavePaymentPersistenceService{

    private final PaymentRepository repository;

    @Override
    public PaymentEntity save(PaymentEntity paymentEntity) {
        return repository.save(paymentEntity);
    }
}

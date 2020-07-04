package br.gabrielsmartins.smartpayment.messaging.adapters.persistence.repository.payments;

import br.gabrielsmartins.smartpayment.messaging.adapters.persistence.entity.payments.PaymentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepository extends MongoRepository<PaymentEntity, String> {

}

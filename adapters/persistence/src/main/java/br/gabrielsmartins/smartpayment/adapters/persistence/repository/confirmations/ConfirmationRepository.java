package br.gabrielsmartins.smartpayment.adapters.persistence.repository.confirmations;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.confirmations.ConfirmationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfirmationRepository extends MongoRepository<ConfirmationEntity, String> {

}

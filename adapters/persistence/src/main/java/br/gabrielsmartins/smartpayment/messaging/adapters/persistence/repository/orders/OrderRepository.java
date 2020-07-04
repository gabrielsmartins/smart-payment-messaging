package br.gabrielsmartins.smartpayment.messaging.adapters.persistence.repository.orders;

import br.gabrielsmartins.smartpayment.messaging.adapters.persistence.entity.orders.OrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface OrderRepository extends MongoRepository<OrderEntity, String> {

}

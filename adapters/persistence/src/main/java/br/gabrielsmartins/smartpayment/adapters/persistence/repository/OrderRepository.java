package br.gabrielsmartins.smartpayment.adapters.persistence.repository;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.OrderEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends ReactiveMongoRepository<OrderEntity, Long> {

}

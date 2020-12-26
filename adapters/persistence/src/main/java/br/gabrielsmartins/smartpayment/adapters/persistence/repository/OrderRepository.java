package br.gabrielsmartins.smartpayment.adapters.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.OrderEntity;


public interface OrderRepository extends MongoRepository<OrderEntity, String> {

}

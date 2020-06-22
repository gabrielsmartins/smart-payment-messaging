package br.gabrielsmartins.smartpayment.messaging.adapters.persistence.repository;

import br.gabrielsmartins.smartpayment.messaging.adapters.persistence.entity.OrderEntity;
import br.gabrielsmartins.smartpayment.messaging.common.stereotype.PersistenceAdapter;

import javax.persistence.EntityManager;

@PersistenceAdapter
public class OrderRepository extends AbstractRepository<OrderEntity> {

    public OrderRepository(EntityManager entityManager) {
        super(entityManager);
    }
}

package br.gabrielsmartins.smartpayment.messaging.adapters.persistence.repository;

import br.gabrielsmartins.smartpayment.messaging.common.stereotype.PersistenceAdapter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@PersistenceAdapter
public class OrderRepository {

    @PersistenceContext
    private EntityManager entityManager;


}

package br.gabrielsmartins.smartpayment.messaging.adapters.persistence.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AbstractRepository<T> {

    @PersistenceContext
    protected EntityManager entityManager;

    public T save(T obj){
        entityManager.persist(obj);
        entityManager.flush();
        return obj;
    }

}

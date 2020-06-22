package br.gabrielsmartins.smartpayment.messaging.adapters.persistence.repository;

import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
public class AbstractRepository<T> {

    protected final EntityManager entityManager;



    public T save(T obj){
        entityManager.persist(obj);
        entityManager.flush();
        return obj;
    }

}

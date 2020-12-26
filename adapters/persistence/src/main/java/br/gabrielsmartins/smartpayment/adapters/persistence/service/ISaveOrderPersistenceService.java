package br.gabrielsmartins.smartpayment.adapters.persistence.service;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.OrderEntity;

public interface ISaveOrderPersistenceService {

    OrderEntity save(OrderEntity orderEntity);

}

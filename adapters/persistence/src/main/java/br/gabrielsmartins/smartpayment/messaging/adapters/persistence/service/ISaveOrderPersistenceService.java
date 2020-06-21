package br.gabrielsmartins.smartpayment.messaging.adapters.persistence.service;

import br.gabrielsmartins.smartpayment.messaging.adapters.persistence.entity.OrderEntity;

public interface ISaveOrderPersistenceService {

    OrderEntity save(OrderEntity orderEntity);

}

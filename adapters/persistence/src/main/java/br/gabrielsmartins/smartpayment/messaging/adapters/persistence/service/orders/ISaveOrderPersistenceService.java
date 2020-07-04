package br.gabrielsmartins.smartpayment.messaging.adapters.persistence.service.orders;

import br.gabrielsmartins.smartpayment.messaging.adapters.persistence.entity.orders.OrderEntity;

public interface ISaveOrderPersistenceService {

    OrderEntity save(OrderEntity orderEntity);

}

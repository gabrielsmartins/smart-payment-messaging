package br.gabrielsmartins.smartpayment.adapters.persistence.service.orders;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.orders.OrderEntity;

public interface ISaveOrderPersistenceService {

    OrderEntity save(OrderEntity orderEntity);

}

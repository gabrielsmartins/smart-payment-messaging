package br.gabrielsmartins.smartpayment.messaging.adapters.persistence.adapter;

import br.gabrielsmartins.smartpayment.messaging.adapters.persistence.entity.OrderEntity;
import br.gabrielsmartins.smartpayment.messaging.adapters.persistence.mapper.OrderPersistenceMapper;
import br.gabrielsmartins.smartpayment.messaging.adapters.persistence.service.SaveOrderPersistenceService;
import br.gabrielsmartins.smartpayment.messaging.application.domain.orders.Order;
import br.gabrielsmartins.smartpayment.messaging.application.ports.out.orders.SaveOrderPort;
import br.gabrielsmartins.smartpayment.messaging.common.stereotype.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class SaveOrderPersistenceAdapter implements SaveOrderPort {

    private final SaveOrderPersistenceService service;
    private final OrderPersistenceMapper mapper;

    @Override
    public Order save(Order order) {
        OrderEntity orderEntity = mapper.mapToEntity(order);
        service.save(orderEntity);
        return  mapper.mapToDomain(orderEntity);
    }
}

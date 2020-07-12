package br.gabrielsmartins.smartpayment.adapters.persistence.adapter.orders;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.orders.OrderEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.mapper.orders.OrderPersistenceMapper;
import br.gabrielsmartins.smartpayment.adapters.persistence.service.orders.SaveOrderPersistenceService;
import br.gabrielsmartins.smartpayment.application.domain.orders.Order;
import br.gabrielsmartins.smartpayment.application.ports.out.orders.SaveOrderPort;
import br.gabrielsmartins.smartpayment.common.stereotype.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class SaveOrderPersistenceAdapter implements SaveOrderPort {

    private final SaveOrderPersistenceService service;
    private final OrderPersistenceMapper mapper;

    @Override
    public Order save(Order order) {
        OrderEntity orderEntity = mapper.mapToEntity(order);
        OrderEntity savedOrderEntity = service.save(orderEntity);
        return mapper.mapToDomain(savedOrderEntity);
    }
}

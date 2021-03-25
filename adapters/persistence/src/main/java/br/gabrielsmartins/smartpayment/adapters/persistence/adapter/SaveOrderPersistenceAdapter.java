package br.gabrielsmartins.smartpayment.adapters.persistence.adapter;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.OrderEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.mapper.OrderPersistenceMapper;
import br.gabrielsmartins.smartpayment.adapters.persistence.service.SaveOrderPersistenceService;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.ports.out.SaveOrderPort;
import br.gabrielsmartins.smartpayment.common.stereotype.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@PersistenceAdapter
@RequiredArgsConstructor
public class SaveOrderPersistenceAdapter implements SaveOrderPort {

    private final SaveOrderPersistenceService service;
    private final OrderPersistenceMapper mapper;

    @Override
    public Mono<Order> save(Order order) {
        OrderEntity orderEntity = mapper.mapToEntity(order);
        return service.save(orderEntity)
                      .flatMap(savedOrderEntity -> {
                          order.setId(savedOrderEntity.getId());
                          return Mono.just(mapper.mapToDomain(savedOrderEntity));
                      });
    }
}

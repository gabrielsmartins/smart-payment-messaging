package br.gabrielsmartins.smartpayment.adapters.persistence.adapter;

import br.gabrielsmartins.smartpayment.adapters.persistence.mapper.OrderPersistenceMapper;
import br.gabrielsmartins.smartpayment.adapters.persistence.service.SearchOrderPersistenceService;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.ports.out.SearchOrderPort;
import br.gabrielsmartins.smartpayment.common.stereotype.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@PersistenceAdapter
@RequiredArgsConstructor
public class SearchOrderPersistenceAdapter implements SearchOrderPort {

    private final SearchOrderPersistenceService service;
    private final OrderPersistenceMapper mapper;

    @Override
    public Mono<Order> findById(Long id) {
        return this.service.findById(id)
                           .flatMap(orderEntity -> Mono.just(mapper.mapToDomain(orderEntity)));
    }

}

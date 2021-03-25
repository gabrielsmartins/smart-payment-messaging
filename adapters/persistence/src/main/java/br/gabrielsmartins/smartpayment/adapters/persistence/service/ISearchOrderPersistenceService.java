package br.gabrielsmartins.smartpayment.adapters.persistence.service;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.OrderEntity;
import reactor.core.publisher.Mono;

public interface ISearchOrderPersistenceService {

    Mono<OrderEntity> findById(Long id);

}

package br.gabrielsmartins.smartpayment.adapters.persistence.service;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.OrderEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SearchOrderPersistenceService implements ISearchOrderPersistenceService{

    private final OrderRepository repository;

    @Override
    public Mono<OrderEntity> findById(Long id) {
        return this.repository.findById(id);
    }

}

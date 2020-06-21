package br.gabrielsmartins.smartpayment.messaging.adapters.persistence.service;


import br.gabrielsmartins.smartpayment.messaging.adapters.persistence.entity.OrderEntity;
import br.gabrielsmartins.smartpayment.messaging.adapters.persistence.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class SaveOrderPersistenceService implements ISaveOrderPersistenceService{

    private final OrderRepository repository;

    @Override
    public OrderEntity save(OrderEntity orderEntity) {
        return repository.save(orderEntity);
    }
}

package br.gabrielsmartins.smartpayment.adapters.persistence.service;


import br.gabrielsmartins.smartpayment.adapters.persistence.entity.OrderEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class SaveOrderPersistenceService implements ISaveOrderPersistenceService{

    private final OrderRepository repository;

    @Override
    public OrderEntity save(OrderEntity orderEntity) {
        return repository.save(orderEntity);
    }
}

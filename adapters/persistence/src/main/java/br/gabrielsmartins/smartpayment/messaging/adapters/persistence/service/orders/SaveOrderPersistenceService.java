package br.gabrielsmartins.smartpayment.messaging.adapters.persistence.service.orders;


import br.gabrielsmartins.smartpayment.messaging.adapters.persistence.entity.orders.OrderEntity;
import br.gabrielsmartins.smartpayment.messaging.adapters.persistence.repository.orders.OrderRepository;
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

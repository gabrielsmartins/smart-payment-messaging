package br.gabrielsmartins.smartpayment.adapters.persistence.service.orders;


import br.gabrielsmartins.smartpayment.adapters.persistence.entity.orders.OrderEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.repository.orders.OrderRepository;
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

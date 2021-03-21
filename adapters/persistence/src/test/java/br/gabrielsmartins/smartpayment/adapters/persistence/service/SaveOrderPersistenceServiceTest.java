package br.gabrielsmartins.smartpayment.adapters.persistence.service;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.OrderEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.entity.OrderItemEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.entity.OrderLogEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.entity.PaymentMethodEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.gabrielsmartins.smartpayment.adapters.persistence.support.OrderEntitySupport.defaultOrderEntity;
import static br.gabrielsmartins.smartpayment.adapters.persistence.support.OrderItemEntitySupport.defaultOrderItemEntity;
import static br.gabrielsmartins.smartpayment.adapters.persistence.support.OrderLogEntitySupport.defaultOrderLogEntity;
import static br.gabrielsmartins.smartpayment.adapters.persistence.support.PaymentMethodEntitySupport.defaultPaymentMethodEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SaveOrderPersistenceServiceTest {

    private SaveOrderPersistenceService service;
    private OrderRepository repository;

    @BeforeEach
    public void setup(){
        this.repository = mock(OrderRepository.class);
       this.service = new SaveOrderPersistenceService(repository);
    }

    @Test
    @DisplayName("Given Order When Save Then Return Saved Order")
    public void givenOrderWhenSaveThenReturnSavedOrder(){
    	OrderEntity orderEntity = defaultOrderEntity().build();

        OrderLogEntity log = defaultOrderLogEntity().build();

		orderEntity.addLog(log);

        OrderItemEntity item = defaultOrderItemEntity().build();

        orderEntity.addItem(item);

        PaymentMethodEntity paymentMethod = defaultPaymentMethodEntity().build();

		orderEntity.addPaymentMethod(paymentMethod);

        when(repository.save(any(OrderEntity.class))).then(invocation -> invocation.getArgument(0));

        OrderEntity savedOrder = this.service.save(orderEntity);
        assertThat(savedOrder).isNotNull();
    }
}

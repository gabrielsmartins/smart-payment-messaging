package br.gabrielsmartins.smartpayment.messaging.adapters.persistence.adapter.orders;

import br.gabrielsmartins.smartpayment.messaging.adapters.persistence.entity.orders.OrderEntity;
import br.gabrielsmartins.smartpayment.messaging.adapters.persistence.mapper.orders.OrderPersistenceMapper;
import br.gabrielsmartins.smartpayment.messaging.adapters.persistence.mapper.orders.OrderPersistenceMapper$OrderPaymentMethodPersistenceMapperImpl;
import br.gabrielsmartins.smartpayment.messaging.adapters.persistence.mapper.orders.OrderPersistenceMapper.OrderPaymentMethodPersistenceMapper;
import br.gabrielsmartins.smartpayment.messaging.adapters.persistence.mapper.orders.OrderPersistenceMapperImpl;
import br.gabrielsmartins.smartpayment.messaging.adapters.persistence.service.orders.SaveOrderPersistenceService;
import br.gabrielsmartins.smartpayment.messaging.application.domain.enums.PaymentType;
import br.gabrielsmartins.smartpayment.messaging.application.domain.orders.Order;
import br.gabrielsmartins.smartpayment.messaging.application.domain.orders.Order.OrderPaymentMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SaveOrderPersistenceAdapterTest {

    private SaveOrderPersistenceAdapter adapter;
    private SaveOrderPersistenceService service;
    private OrderPersistenceMapper mapper;

    @BeforeEach
    public void setup(){
        this.service = mock(SaveOrderPersistenceService.class);
        OrderPaymentMethodPersistenceMapper paymentMethodPersistenceMapper = new OrderPersistenceMapper$OrderPaymentMethodPersistenceMapperImpl();
        this.mapper = new OrderPersistenceMapperImpl(paymentMethodPersistenceMapper);
        this.adapter = new SaveOrderPersistenceAdapter(service,mapper);
    }

    @Test
    @DisplayName("Given Order When Save Then Return Saved Order")
    public void givenOrderWhenSaveThenReturnSavedOrder(){

        Order order = Order.builder()
                            .withId(UUID.randomUUID().toString())
                            .withPaymentNumberIdentifier(UUID.randomUUID().toString())
                            .withDueDate(LocalDate.now())
                            .withPaymentDate(LocalDate.now())
                            .withTotalAmount(new BigDecimal(1500.00))
                            .withTotalAmountPaid(new BigDecimal(1400.00))
                            .build();

        OrderPaymentMethod paymentMethod = OrderPaymentMethod.builder()
                                            .withId(1L)
                                            .withDiscount(new BigDecimal(100.00))
                                            .withTotalAmountPaid(new BigDecimal(1400.00))
                                            .withPaymentType(PaymentType.CREDIT_CARD)
                                            .build();

        order.addPaymentMethod(paymentMethod);

        when(service.save(any(OrderEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Order savedOrder = adapter.save(order);
        assertThat(savedOrder).isNotNull();
    }

}

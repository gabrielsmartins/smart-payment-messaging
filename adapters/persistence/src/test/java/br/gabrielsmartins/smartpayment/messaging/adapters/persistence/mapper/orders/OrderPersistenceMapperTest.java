package br.gabrielsmartins.smartpayment.messaging.adapters.persistence.mapper.orders;

import br.gabrielsmartins.smartpayment.messaging.adapters.persistence.entity.orders.OrderEntity;
import br.gabrielsmartins.smartpayment.messaging.adapters.persistence.entity.orders.OrderEntity.OrderPaymentMethodEntity;
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

public class OrderPersistenceMapperTest {

    private OrderPersistenceMapper mapper;
    private OrderPersistenceMapper.OrderPaymentMethodPersistenceMapper paymentMethodPersistenceMapper;


    @BeforeEach
    public void setup(){
        this.paymentMethodPersistenceMapper = new OrderPersistenceMapper$OrderPaymentMethodPersistenceMapperImpl();
        this.mapper = new OrderPersistenceMapperImpl(paymentMethodPersistenceMapper);
    }

    @Test
    @DisplayName("Given Order Entity When Map Then Return Order Domain")
    public void givenOrderEntityWhenMapThenReturnOrderDomain(){

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

        OrderEntity orderEntity = this.mapper.mapToEntity(order);

        assertThat(orderEntity.getId()).isEqualTo(order.getId());
        assertThat(orderEntity.getPaymentNumberIdentifier()).isEqualTo(order.getPaymentNumberIdentifier());
        assertThat(orderEntity.getDueDate()).isEqualTo(order.getDueDate());
        assertThat(orderEntity.getPaymentDate()).isEqualTo(order.getPaymentDate());
        assertThat(orderEntity.getTotalAmount()).isEqualTo(order.getTotalAmount());
        assertThat(orderEntity.getTotalAmountPaid()).isEqualTo(order.getTotalAmountPaid());
        assertThat(orderEntity.getPaymentMethods().size()).isEqualTo(order.getPaymentMethods().size());
    }

    @Test
    @DisplayName("Given Order Domain When Map Then Return Order Entity")
    public void givenOrderDomainWhenMapThenReturnOrderEntity(){


        OrderEntity orderEntity = OrderEntity.builder()
                                                .withId(UUID.randomUUID().toString())
                                                .withPaymentNumberIdentifier(UUID.randomUUID().toString())
                                                .withDueDate(LocalDate.now())
                                                .withPaymentDate(LocalDate.now())
                                                .withTotalAmount(new BigDecimal(1500.00))
                                                .withTotalAmountPaid(new BigDecimal(1400.00))
                                                .build();

        OrderPaymentMethodEntity paymentMethod = OrderPaymentMethodEntity.builder()
                                                                            .withId(1L)
                                                                            .withOrder(orderEntity)
                                                                            .withDiscount(new BigDecimal(100.00))
                                                                            .withTotalAmountPaid(new BigDecimal(1400.00))
                                                                            .withPaymentType(PaymentType.CREDIT_CARD)
                                                                            .build();

        orderEntity.addPaymentMethod(paymentMethod);

        Order order = this.mapper.mapToDomain(orderEntity);

        assertThat(order.getId()).isEqualTo(orderEntity.getId());
        assertThat(order.getPaymentNumberIdentifier()).isEqualTo(orderEntity.getPaymentNumberIdentifier());
        assertThat(order.getDueDate()).isEqualTo(orderEntity.getDueDate());
        assertThat(order.getPaymentDate()).isEqualTo(orderEntity.getPaymentDate());
        assertThat(order.getTotalAmount()).isEqualTo(orderEntity.getTotalAmount());
        assertThat(order.getTotalAmountPaid()).isEqualTo(orderEntity.getTotalAmountPaid());
        assertThat(order.getPaymentMethods().size()).isEqualTo(orderEntity.getPaymentMethods().size());
    }
}

package br.gabrielsmartins.smartpayment.adapters.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.OrderEntity;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import br.gabrielsmartins.smartpayment.application.domain.enums.PaymentType;

public class OrderPersistenceMapperTest {

    private OrderPersistenceMapper mapper;
    
    @BeforeEach
    public void setup(){
        this.mapper = new OrderPersistenceMapperImpl();
    }

    @Test
    @DisplayName("Given Order Entity When Map Then Return Order Domain")
    public void givenOrderEntityWhenMapThenReturnOrderDomain(){

    	Order order = Order.builder()
                .withId(UUID.randomUUID().toString())
                .withCustomerId(UUID.randomUUID().toString())
                .withCreatedAt(LocalDateTime.now())
                .withFinishedAt(LocalDateTime.now())
                .withStatus(OrderStatus.COMPLETED)
                .withTotalAmount(new BigDecimal(1500.00))
                .withTotalDiscount(new BigDecimal(1400.00))
                .build();

    	order.addLog(LocalDateTime.now(), OrderStatus.REQUESTED);
    	order.addItem(UUID.randomUUID().toString(), BigDecimal.ZERO);
    	order.addPaymentMethod(PaymentType.CREDIT_CARD, BigDecimal.TEN);

        OrderEntity orderEntity = this.mapper.mapToEntity(order);

        assertThat(orderEntity.getId()).isEqualTo(order.getId());
        assertThat(orderEntity.getCustomerId()).isEqualTo(order.getCustomerId());
        assertThat(orderEntity.getCreatedAt()).isEqualTo(order.getCreatedAt());
        assertThat(orderEntity.getFinishedAt()).isEqualTo(order.getFinishedAt());
        assertThat(orderEntity.getTotalAmount()).isEqualTo(order.getTotalAmount());
        assertThat(orderEntity.getTotalDiscount()).isEqualTo(order.getTotalDiscount());
        assertThat(orderEntity.getStatus()).isEqualTo(order.getStatus());
        assertThat(orderEntity.getState()).isEqualTo(order.getState());
        assertThat(orderEntity.getLogs().size()).isEqualTo(order.getLogs().size());
        assertThat(orderEntity.getItems().size()).isEqualTo(order.getItems().size());
        assertThat(orderEntity.getPaymentMethods().size()).isEqualTo(order.getPaymentMethods().size());
    }

    @Test
    @DisplayName("Given Order Domain When Map Then Return Order Entity")
    public void givenOrderDomainWhenMapThenReturnOrderEntity(){


    	OrderEntity orderEntity = OrderEntity.builder()
						                .withId(UUID.randomUUID().toString())
						                .withCustomerId(UUID.randomUUID().toString())
						                .withCreatedAt(LocalDateTime.now())
						                .withFinishedAt(LocalDateTime.now())
						                .withStatus(OrderStatus.COMPLETED)
						                .withTotalAmount(new BigDecimal(1500.00))
						                .withTotalDiscount(new BigDecimal(1400.00))
						                .build();

    	orderEntity.addLog(LocalDateTime.now(), OrderStatus.REQUESTED);
    	orderEntity.addItem(UUID.randomUUID().toString(), BigDecimal.ZERO);
    	orderEntity.addPaymentMethod(PaymentType.CREDIT_CARD, BigDecimal.TEN);


        Order order = this.mapper.mapToDomain(orderEntity);

        assertThat(order.getId()).isEqualTo(orderEntity.getId());
        assertThat(order.getCustomerId()).isEqualTo(orderEntity.getCustomerId());
        assertThat(order.getCreatedAt()).isEqualTo(orderEntity.getCreatedAt());
        assertThat(order.getFinishedAt()).isEqualTo(orderEntity.getFinishedAt());
        assertThat(order.getTotalAmount()).isEqualTo(orderEntity.getTotalAmount());
        assertThat(order.getTotalDiscount()).isEqualTo(orderEntity.getTotalDiscount());
        assertThat(order.getStatus()).isEqualTo(orderEntity.getStatus());
        assertThat(order.getState()).isEqualTo(order.getState());
        assertThat(order.getLogs().size()).isEqualTo(orderEntity.getLogs().size());
        assertThat(order.getItems().size()).isEqualTo(orderEntity.getItems().size());
        assertThat(order.getPaymentMethods().size()).isEqualTo(orderEntity.getPaymentMethods().size());
    }
}

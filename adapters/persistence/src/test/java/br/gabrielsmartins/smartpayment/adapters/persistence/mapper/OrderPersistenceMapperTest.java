package br.gabrielsmartins.smartpayment.adapters.persistence.mapper;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.OrderEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.entity.OrderItemEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.entity.OrderLogEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.entity.PaymentMethodEntity;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.OrderItem;
import br.gabrielsmartins.smartpayment.application.domain.PaymentMethod;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import br.gabrielsmartins.smartpayment.application.domain.enums.PaymentType;
import br.gabrielsmartins.smartpayment.application.domain.state.OrderLog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderPersistenceMapperTest {

    private OrderPersistenceMapper mapper;
    
    @BeforeEach
    public void setup(){
        OrderLogPersistenceMapper orderLogPersistenceMapper = new OrderLogPersistenceMapperImpl();
        OrderItemPersistenceMapper itemPersistenceMapper = new OrderItemPersistenceMapperImpl();
        PaymentMethodPersistenceMapper paymentMethodPersistenceMapper = new PaymentMethodPersistenceMapperImpl();
        this.mapper = new OrderPersistenceMapperImpl(orderLogPersistenceMapper,itemPersistenceMapper, paymentMethodPersistenceMapper);
    }

    @Test
    @DisplayName("Given Order Entity When Map Then Return Order Domain")
    public void givenOrderEntityWhenMapThenReturnOrderDomain(){

    	Order order = Order.builder()
                .withId(UUID.randomUUID().toString())
                .withCustomerId(UUID.randomUUID())
                .withCreatedAt(LocalDateTime.now())
                .withFinishedAt(LocalDateTime.now())
                .withStatus(OrderStatus.COMPLETED)
                .withTotalAmount(BigDecimal.valueOf(1500.00))
                .withTotalDiscount(BigDecimal.valueOf(1400.00))
                .build();

        OrderLog orderLog = OrderLog.builder()
                .withStatus(OrderStatus.COMPLETED)
                .withDatetime(LocalDateTime.now())
                .build();

    	order.addLog(orderLog);

        OrderItem orderItem = OrderItem.builder()
                .withProductId(UUID.randomUUID())
                .withQuantity(1)
                .withAmount(BigDecimal.TEN)
                .build();

    	order.addItem(orderItem);

        PaymentMethod paymentMethod = PaymentMethod.builder()
                .withPaymentType(PaymentType.CASH)
                .withAmount(BigDecimal.TEN)
                .build();

        order.addPaymentMethod(paymentMethod);

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
						                .withCustomerId(UUID.randomUUID())
						                .withCreatedAt(LocalDateTime.now())
						                .withFinishedAt(LocalDateTime.now())
						                .withStatus(OrderStatus.COMPLETED)
						                .withTotalAmount(BigDecimal.valueOf(1500.00))
						                .withTotalDiscount(BigDecimal.valueOf(1400.00))
						                .build();


        OrderLogEntity orderLogEntity = OrderLogEntity.builder()
                                                    .withStatus(OrderStatus.COMPLETED)
                                                    .withDatetime(LocalDateTime.now())
                                                    .build();


    	orderEntity.addLog(orderLogEntity);

        OrderItemEntity item = OrderItemEntity.builder()
                .withProductId(UUID.randomUUID())
                .withQuantity(1)
                .withAmount(BigDecimal.TEN)
                .build();


        orderEntity.addItem(item);

        PaymentMethodEntity paymentMethodEntity = PaymentMethodEntity.builder()
                                                                .withPaymentType(PaymentType.CASH.getDescription())
                                                                .withAmount(BigDecimal.TEN)
                                                                .build();


    	orderEntity.addPaymentMethod(paymentMethodEntity);


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

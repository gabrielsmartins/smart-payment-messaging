package br.gabrielsmartins.smartpayment.adapters.persistence.mapper;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.OrderEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.entity.OrderItemEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.entity.OrderLogEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.entity.PaymentMethodEntity;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.OrderItem;
import br.gabrielsmartins.smartpayment.application.domain.PaymentMethod;
import br.gabrielsmartins.smartpayment.application.domain.state.OrderLog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.gabrielsmartins.smartpayment.adapters.persistence.support.OrderEntitySupport.defaultOrderEntity;
import static br.gabrielsmartins.smartpayment.adapters.persistence.support.OrderItemEntitySupport.defaultOrderItemEntity;
import static br.gabrielsmartins.smartpayment.adapters.persistence.support.OrderLogEntitySupport.defaultOrderLogEntity;
import static br.gabrielsmartins.smartpayment.adapters.persistence.support.PaymentMethodEntitySupport.defaultPaymentMethodEntity;
import static br.gabrielsmartins.smartpayment.application.support.OrderItemSupport.defaultOrderItem;
import static br.gabrielsmartins.smartpayment.application.support.OrderLogSupport.defaultOrderLog;
import static br.gabrielsmartins.smartpayment.application.support.OrderSupport.defaultOrder;
import static br.gabrielsmartins.smartpayment.application.support.PaymentMethodSupport.defaultPaymentMethod;
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

    	Order order = defaultOrder().build();

        OrderLog orderLog = defaultOrderLog().build();

    	order.addLog(orderLog);

        OrderItem orderItem = defaultOrderItem().build();

    	order.addItem(orderItem);

        PaymentMethod paymentMethod = defaultPaymentMethod().build();

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

    	OrderEntity orderEntity = defaultOrderEntity().build();

        OrderLogEntity orderLogEntity = defaultOrderLogEntity().build();

    	orderEntity.addLog(orderLogEntity);

        OrderItemEntity item = defaultOrderItemEntity().build();

        orderEntity.addItem(item);

        PaymentMethodEntity paymentMethodEntity = defaultPaymentMethodEntity().build();

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

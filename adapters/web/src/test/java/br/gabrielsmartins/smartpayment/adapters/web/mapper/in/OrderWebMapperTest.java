package br.gabrielsmartins.smartpayment.adapters.web.mapper.in;

import br.gabrielsmartins.smartpayment.adapters.web.adapter.in.dto.OrderDTO;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.OrderItem;
import br.gabrielsmartins.smartpayment.application.domain.PaymentMethod;
import br.gabrielsmartins.smartpayment.application.domain.state.OrderLog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static br.gabrielsmartins.smartpayment.application.support.OrderLogSupport.defaultOrderLog;
import static br.gabrielsmartins.smartpayment.application.support.OrderSupport.defaultOrder;
import static br.gabrielsmartins.smartpayment.application.support.PaymentMethodSupport.defaultPaymentMethod;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderWebMapperTest {

    private OrderWebMapper mapper;

    @BeforeEach
    public void setup(){
        var orderLogWebMapper = new OrderLogWebMapperImpl();
        var orderItemWebMapper = new OrderItemWebMapperImpl();
        var paymentMethodWebMapper = new PaymentMethodWebMapperImpl();
        this.mapper = new OrderWebMapperImpl(orderLogWebMapper, orderItemWebMapper, paymentMethodWebMapper);
    }

    @Test
    @DisplayName("Given Order Domain When Map Then Return Order DTO")
    public void givenOrderDomainWhenMapThenReturnOrderDTO(){

        Order order = defaultOrder().build();

        OrderLog orderLog = defaultOrderLog().build();

        order.addLog(orderLog);

        OrderItem item = new OrderItem();
        item.setProductId(UUID.randomUUID());
        item.setQuantity(10);
        item.setAmount(BigDecimal.TEN);

        order.addItem(item);

        PaymentMethod paymentMethod = defaultPaymentMethod().build();

        order.addPaymentMethod(paymentMethod);

        OrderDTO orderDTO = this.mapper.mapToDto(order);

        assertThat(orderDTO.getId()).isEqualTo(order.getId());
        assertThat(orderDTO.getCustomerId()).isEqualTo(order.getCustomerId());
        assertThat(orderDTO.getCreatedAt()).isEqualTo(order.getCreatedAt());
        assertThat(orderDTO.getFinishedAt()).isEqualTo(order.getFinishedAt());
        assertThat(orderDTO.getTotalAmount()).isEqualTo(order.getTotalAmount());
        assertThat(orderDTO.getTotalDiscount()).isEqualTo(order.getTotalDiscount());
        assertThat(orderDTO.getStatus()).isEqualTo(order.getStatus().getDescription());
        assertThat(orderDTO.getLogs().size()).isEqualTo(order.getLogs().size());
        assertThat(orderDTO.getItems().size()).isEqualTo(order.getItems().size());
        assertThat(orderDTO.getPaymentMethods().size()).isEqualTo(order.getPaymentMethods().size());
    }

}

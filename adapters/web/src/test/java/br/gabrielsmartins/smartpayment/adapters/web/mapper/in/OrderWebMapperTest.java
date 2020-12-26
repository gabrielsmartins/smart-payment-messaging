package br.gabrielsmartins.smartpayment.adapters.web.mapper.in;

import br.gabrielsmartins.smartpayment.adapters.web.adapter.in.dto.OrderDTO;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import br.gabrielsmartins.smartpayment.application.domain.enums.PaymentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderWebMapperTest {

    private OrderWebMapper mapper;

    @BeforeEach
    public void setup(){
        this.mapper = new OrderWebMapperImpl();
    }

    @Test
    @DisplayName("Given Order Domain When Map Then Return Order DTO")
    public void givenOrderDomainWhenMapThenReturnOrderDTO(){

    	Order order = Order.builder()
                .withId(UUID.randomUUID().toString())
                .withCustomerId(UUID.randomUUID().toString())
                .withCreatedAt(LocalDateTime.now())
                .withFinishedAt(LocalDateTime.now())
                .withStatus(OrderStatus.COMPLETED)
                .withTotalAmount(BigDecimal.valueOf(1500.00))
                .withTotalDiscount(BigDecimal.valueOf(1400.00))
                .build();

    	order.addLog(LocalDateTime.now(), OrderStatus.REQUESTED);
    	order.addItem(UUID.randomUUID().toString(), BigDecimal.ZERO);
    	order.addPaymentMethod(PaymentType.CREDIT_CARD, BigDecimal.TEN);

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
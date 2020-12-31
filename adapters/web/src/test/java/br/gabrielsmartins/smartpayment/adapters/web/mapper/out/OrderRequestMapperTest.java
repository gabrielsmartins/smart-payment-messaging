package br.gabrielsmartins.smartpayment.adapters.web.mapper.out;

import br.gabrielsmartins.smartpayment.adapters.web.adapter.out.dto.OrderRequestDTO;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.Order.OrderItem;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import br.gabrielsmartins.smartpayment.application.domain.enums.PaymentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderRequestMapperTest {

    private OrderRequestMapper mapper;

    @BeforeEach
    public void setup(){
        this.mapper = new OrderRequestMapperImpl();
    }

    @Test
    @DisplayName("given Order When Map Then Return Request Dto")
    public void givenOrderWhenMapThenReturnRequestDto(){

        Order order = Order.builder()
                .withId(UUID.randomUUID().toString())
                .withCustomerId(UUID.randomUUID())
                .withCreatedAt(LocalDateTime.now())
                .withFinishedAt(LocalDateTime.now())
                .withStatus(OrderStatus.COMPLETED)
                .withTotalAmount(BigDecimal.valueOf(1500.00))
                .withTotalDiscount(BigDecimal.valueOf(1400.00))
                .build();

        order.addLog(LocalDateTime.now(), OrderStatus.REQUESTED);

        OrderItem item = new OrderItem();
        item.setProductId(UUID.randomUUID());
        item.setQuantity(10);
        item.setAmount(BigDecimal.TEN);

        order.addItem(item);

        order.addPaymentMethod(PaymentType.CREDIT_CARD, BigDecimal.TEN);

        OrderRequestDTO orderRequestDTO = this.mapper.mapToDto(order);

        assertThat(orderRequestDTO.getId()).isEqualTo(order.getId());
        assertThat(orderRequestDTO.getCustomerId()).isEqualTo(order.getCustomerId());
        assertThat(orderRequestDTO.getCreatedAt()).isEqualTo(order.getCreatedAt());
        assertThat(orderRequestDTO.getFinishedAt()).isEqualTo(order.getFinishedAt());
        assertThat(orderRequestDTO.getTotalAmount()).isEqualTo(order.getTotalAmount());
        assertThat(orderRequestDTO.getTotalDiscount()).isEqualTo(order.getTotalDiscount());;
        assertThat(orderRequestDTO.getItems().size()).isEqualTo(order.getItems().size());
        assertThat(orderRequestDTO.getPaymentMethods().size()).isEqualTo(order.getPaymentMethods().size());
    }
}

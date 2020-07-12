package br.gabrielsmartins.smartpayment.adapters.web.mapper.orders;

import br.gabrielsmartins.smartpayment.adapters.web.dto.orders.OrderDTO;
import br.gabrielsmartins.smartpayment.application.domain.enums.PaymentType;
import br.gabrielsmartins.smartpayment.application.domain.orders.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
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
                .withPaymentNumberIdentifier(UUID.randomUUID().toString())
                .withDueDate(LocalDate.now())
                .withPaymentDate(LocalDate.now())
                .withTotalAmount(new BigDecimal(1500.00))
                .withTotalAmountPaid(new BigDecimal(1500.00))
                .build();

        Order.OrderPaymentMethod paymentMethod = Order.OrderPaymentMethod.builder().withId(1L)
                                        .withPaymentType(PaymentType.CREDIT_CARD)
                                        .withDiscount(new BigDecimal(0.00))
                                        .withTotalAmountPaid(new BigDecimal(1500.00))
                                        .build();

        order.addPaymentMethod(paymentMethod);


        OrderDTO orderDTO = this.mapper.mapToDto(order);

        assertThat(orderDTO.getId()).isEqualTo(order.getId());
        assertThat(orderDTO.getPaymentNumberIdentifier()).isEqualTo(order.getPaymentNumberIdentifier());
        assertThat(orderDTO.getDueDate()).isEqualTo(order.getDueDate());
        assertThat(orderDTO.getPaymentDate()).isEqualTo(order.getPaymentDate());
        assertThat(orderDTO.getTotalAmount()).isEqualTo(order.getTotalAmount());
        assertThat(orderDTO.getTotalAmountPaid()).isEqualTo(order.getTotalAmountPaid());
    }

}

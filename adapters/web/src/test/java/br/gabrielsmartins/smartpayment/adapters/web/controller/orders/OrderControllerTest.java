package br.gabrielsmartins.smartpayment.adapters.web.controller.orders;

import br.gabrielsmartins.smartpayment.adapters.web.dto.orders.OrderDTO;
import br.gabrielsmartins.smartpayment.adapters.web.mapper.orders.OrderWebMapperImpl;
import br.gabrielsmartins.smartpayment.application.domain.enums.PaymentType;
import br.gabrielsmartins.smartpayment.application.domain.orders.Order;
import br.gabrielsmartins.smartpayment.application.ports.in.orders.SaveOrderUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper;

    @MockBean
    private SaveOrderUseCase saveOrderUseCase;

    @SpyBean
    private OrderWebMapperImpl orderWebMapper;

    @BeforeEach
    public void setup(){
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
    }

    @Test
    @DisplayName("Given Order When Accept Then Return Saved Order")
    public void givenOrderWhenAcceptThenReturnSavedOrder() throws Exception {

        OrderDTO orderDTO = OrderDTO.builder()
                                    .dueDate(LocalDate.now())
                                    .paymentDate(LocalDate.now())
                                    .totalAmount(new BigDecimal(1500))
                                    .totalAmountPaid(new BigDecimal(1500))
                                    .paymentNumberIdentifier(UUID.randomUUID().toString())
                                    .build();

        String content = this.mapper.writeValueAsString(orderDTO);


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

        when(saveOrderUseCase.save(any(Order.class))).thenReturn(order);

        mockMvc.perform(post("/orders")
                                 .content(content)
                                 .header("Content-type","application/json")
                                 .header("Accept","application/json"))
                       .andExpect(status().isAccepted());
    }

}

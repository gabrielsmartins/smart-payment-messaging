package br.gabrielsmartins.smartpayment.adapters.web.adapters.in;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.gabrielsmartins.smartpayment.adapters.web.adapter.in.SubmitOrderController;
import br.gabrielsmartins.smartpayment.adapters.web.adapter.in.dto.OrderDTO;
import br.gabrielsmartins.smartpayment.adapters.web.mapper.in.OrderWebMapper;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import br.gabrielsmartins.smartpayment.application.domain.enums.PaymentType;
import br.gabrielsmartins.smartpayment.application.ports.in.SaveOrderUseCase;

@WebMvcTest(SubmitOrderController.class)
public class SubmitOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper;

    @MockBean
    private SaveOrderUseCase saveOrderUseCase;

    @SpyBean
    private OrderWebMapper orderWebMapper;

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


        when(saveOrderUseCase.save(any(Order.class))).thenReturn(order);

        mockMvc.perform(post("/orders")
                                 .content(content)
                                 .header("Content-type","application/json")
                                 .header("Accept","application/json"))
                       .andExpect(status().isAccepted());
    }

}

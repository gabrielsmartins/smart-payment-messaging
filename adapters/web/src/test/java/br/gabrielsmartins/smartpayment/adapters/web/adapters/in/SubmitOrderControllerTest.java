package br.gabrielsmartins.smartpayment.adapters.web.adapters.in;

import br.gabrielsmartins.smartpayment.adapters.web.adapter.in.SubmitOrderController;
import br.gabrielsmartins.smartpayment.adapters.web.adapter.in.dto.OrderDTO;
import br.gabrielsmartins.smartpayment.adapters.web.mapper.in.OrderWebMapper$OrderItemWebMapperImpl;
import br.gabrielsmartins.smartpayment.adapters.web.mapper.in.OrderWebMapperImpl;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.Order.OrderItem;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import br.gabrielsmartins.smartpayment.application.domain.enums.PaymentType;
import br.gabrielsmartins.smartpayment.application.ports.in.SaveOrderUseCase;
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
import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SubmitOrderController.class)
public class SubmitOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper;

    @MockBean
    private SaveOrderUseCase saveOrderUseCase;

    @SpyBean
    private OrderWebMapperImpl orderWebMapper;

    @SpyBean
    private OrderWebMapper$OrderItemWebMapperImpl orderItemWebMapper;

    @BeforeEach
    public void setup(){
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
    }

    @Test
    @DisplayName("Given Order When Accept Then Return Saved Order")
    public void givenOrderWhenAcceptThenReturnSavedOrder() throws Exception {

        OrderDTO orderDTO = OrderDTO.builder()
                                    .withId(UUID.randomUUID().toString())
                                    .withCustomerId(UUID.randomUUID())
                                    .withCreatedAt(LocalDateTime.now())
                                    .withFinishedAt(LocalDateTime.now())
                                    .withStatus(OrderStatus.COMPLETED.getDescription())
                                    .withTotalAmount(BigDecimal.valueOf(1500.00))
                                    .withTotalDiscount(BigDecimal.valueOf(1400.00))
                                    .build();

        String content = this.mapper.writeValueAsString(orderDTO);


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

        when(saveOrderUseCase.save(any(Order.class))).thenReturn(order);

        mockMvc.perform(post("/orders")
                                 .content(content)
                                 .header("Content-type","application/json")
                                 .header("Accept","application/json"))
                       .andExpect(status().isAccepted());
    }

}

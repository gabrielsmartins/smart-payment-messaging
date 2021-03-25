package br.gabrielsmartins.smartpayment.adapters.web.adapters.in;

import br.gabrielsmartins.smartpayment.adapters.web.adapter.in.SubmitOrderController;
import br.gabrielsmartins.smartpayment.adapters.web.adapter.in.dto.OrderDTO;
import br.gabrielsmartins.smartpayment.adapters.web.mapper.in.OrderItemWebMapperImpl;
import br.gabrielsmartins.smartpayment.adapters.web.mapper.in.OrderLogWebMapperImpl;
import br.gabrielsmartins.smartpayment.adapters.web.mapper.in.OrderWebMapperImpl;
import br.gabrielsmartins.smartpayment.adapters.web.mapper.in.PaymentMethodWebMapperImpl;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.OrderItem;
import br.gabrielsmartins.smartpayment.application.domain.PaymentMethod;
import br.gabrielsmartins.smartpayment.application.domain.state.OrderLog;
import br.gabrielsmartins.smartpayment.application.ports.in.SubmitOrderUseCase;
import br.gabrielsmartins.smartpayment.application.ports.in.SubmitOrderUseCase.SubmitOrderCommand;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import reactor.core.publisher.Mono;

import static br.gabrielsmartins.smartpayment.adapters.web.support.OrderDTOSupport.defaultOrderDto;
import static br.gabrielsmartins.smartpayment.application.support.OrderItemSupport.defaultOrderItem;
import static br.gabrielsmartins.smartpayment.application.support.OrderLogSupport.defaultOrderLog;
import static br.gabrielsmartins.smartpayment.application.support.OrderSupport.defaultOrder;
import static br.gabrielsmartins.smartpayment.application.support.PaymentMethodSupport.defaultPaymentMethod;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SubmitOrderController.class)
@Import({OrderLogWebMapperImpl.class,
        OrderItemWebMapperImpl.class,
        PaymentMethodWebMapperImpl.class})
public class SubmitOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper;

    @MockBean
    private SubmitOrderUseCase useCase;

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

        OrderDTO orderDTO = defaultOrderDto().build();

        String content = this.mapper.writeValueAsString(orderDTO);

        Order order = defaultOrder().build();

        OrderLog orderLog = defaultOrderLog().build();

        order.addLog(orderLog);

        OrderItem item = defaultOrderItem().build();

        order.addItem(item);

        PaymentMethod paymentMethod = defaultPaymentMethod().build();

        order.addPaymentMethod(paymentMethod);

        when(useCase.submit(any(SubmitOrderCommand.class))).thenReturn(Mono.just(order));

        mockMvc.perform(post("/orders")
                                 .content(content)
                                 .header("Content-type","application/json")
                                 .header("Accept","application/json"))
                       .andExpect(status().isAccepted());
    }

}

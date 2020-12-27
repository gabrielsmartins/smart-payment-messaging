package br.gabrielsmartins.smartpayment.adapters.messaging.adapter.in;

import br.gabrielsmartins.smartpayment.adapters.messaging.config.TopicProperties;
import br.gabrielsmartins.smartpayment.adapters.messaging.mapper.in.OrderMessagingMapper;
import br.gabrielsmartins.smartpayment.application.ports.in.SubmitOrderUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@EmbeddedKafka(topics = TopicProperties.ORDER_REQUESTED)
public class OrderConsumerTest {

    @InjectMocks
    private OrderConsumer consumer;

    @MockBean
    private SubmitOrderUseCase useCase;

    @MockBean
    private OrderMessagingMapper mapper;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Given Message When Receive Then Submit Order")
    public void givenMessageWhenReceiveThenSubmitOrder(){


    }

}

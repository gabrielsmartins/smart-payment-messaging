package br.gabrielsmartins.smartpayment.adapters.messaging.adapter.in;

import br.gabrielsmartins.schemas.NewOrder;
import br.gabrielsmartins.smartpayment.adapters.messaging.mapper.in.OrderMessagingMapper;
import br.gabrielsmartins.smartpayment.application.ports.in.SaveOrderUseCase;
import br.gabrielsmartins.smartpayment.common.stereotype.MessagingAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;

@MessagingAdapter
@KafkaListener(topics = {"${topics.input.order-requested}"})
@Slf4j
@RequiredArgsConstructor
public class OrderConsumer {

    private final SaveOrderUseCase saveOrderUseCase;
    private final OrderMessagingMapper mapper;

    @KafkaHandler
    public void consume(@Headers MessageHeaders headers,  @Payload NewOrder message){

    }

    @KafkaHandler(isDefault = true)
    public void consume(Object object){
      log.warn("Unrecognized message: {}", object);
    }

}

package br.gabrielsmartins.smartpayment.adapters.messaging.adapter.out;

import br.gabrielsmartins.smartpayment.adapters.messaging.adapter.out.mapper.factory.OrderStatusMessagingProducerMapper;
import br.gabrielsmartins.smartpayment.adapters.messaging.config.TopicProperties;
import br.gabrielsmartins.smartpayment.adapters.messaging.adapter.out.mapper.factory.OrderStatusMapperMessagingProducerFactory;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.ports.out.SendOrderStatusPort;
import br.gabrielsmartins.smartpayment.common.stereotype.MessagingAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecord;
import org.springframework.kafka.core.KafkaTemplate;
import reactor.core.publisher.Mono;

@MessagingAdapter
@RequiredArgsConstructor
@Slf4j
public class OrderStatusProducer implements SendOrderStatusPort {

    private final KafkaTemplate<String, SpecificRecord> template;
    private final TopicProperties topicProperties;
    private final OrderStatusMapperMessagingProducerFactory factory;

    @Override
    public Mono<Void> send(Order order) {
        OrderStatusMessagingProducerMapper<?> mapper = factory.createMapper(order.getStatus());
        SpecificRecord message = mapper.mapToMessage(order);
        String topic = topicProperties.getOutputTopic(TopicProperties.ORDER_STATUS_UPDATED);
        log.info("Sending message: {}", message);
        template.send(topic, String.valueOf(order.getId()), message);
        return Mono.empty().then();
    }

}

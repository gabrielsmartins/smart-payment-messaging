package br.gabrielsmartins.smartpayment.adapters.messaging.adapter.out;

import br.gabrielsmartins.smartpayment.adapters.messaging.config.TopicProperties;
import br.gabrielsmartins.smartpayment.adapters.messaging.mapper.out.factory.OrderStatusMessagingOutputMapper;
import br.gabrielsmartins.smartpayment.adapters.messaging.mapper.out.factory.OrderStatusMapperMessagingOutputFactory;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.ports.out.SendOrderStatusPort;
import br.gabrielsmartins.smartpayment.common.stereotype.MessagingAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecord;
import org.springframework.kafka.core.KafkaTemplate;

@MessagingAdapter
@RequiredArgsConstructor
@Slf4j
public class OrderStatusProducer implements SendOrderStatusPort {

    private final KafkaTemplate<String, SpecificRecord> template;
    private final TopicProperties topicProperties;
    private final OrderStatusMapperMessagingOutputFactory factory;

    @Override
    public void send(Order order) {
        OrderStatusMessagingOutputMapper<?> mapper = factory.createMapper(order.getStatus());
        SpecificRecord message = mapper.mapToMessage(order);
        String topic = topicProperties.getOutputTopic(TopicProperties.ORDER_STATUS_UPDATED);
        log.info("Sending message: {}", message);
        template.send(topic, order.getId(), message);
    }
}

package br.gabrielsmartins.smartpayment.adapters.messaging.adapter.out;


import br.gabrielsmartins.smartpayment.adapters.messaging.config.TopicProperties;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.OrderItem;
import br.gabrielsmartins.smartpayment.application.domain.PaymentMethod;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import br.gabrielsmartins.smartpayment.application.domain.state.OrderLog;
import br.gabrielsmartins.smartpayment.application.ports.in.SubmitOrderUseCase;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.Map;

import static br.gabrielsmartins.smartpayment.application.support.OrderItemSupport.defaultOrderItem;
import static br.gabrielsmartins.smartpayment.application.support.OrderLogSupport.defaultOrderLog;
import static br.gabrielsmartins.smartpayment.application.support.OrderSupport.defaultOrder;
import static br.gabrielsmartins.smartpayment.application.support.PaymentMethodSupport.defaultPaymentMethod;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@MockBeans({
        @MockBean(SubmitOrderUseCase.class)
})
@ExtendWith(SpringExtension.class)
@EmbeddedKafka(partitions = 1, controlledShutdown = true)
@ActiveProfiles("test")
public class OrderStatusProducerTest {

    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;

    @Autowired
    private OrderStatusProducer producer;

    @Autowired
    private TopicProperties topicProperties;

    private String topic;

    @BeforeEach
    public void setup(){
        this.topic = this.topicProperties.getOutputTopic(TopicProperties.ORDER_STATUS_UPDATED);
    }

    @Test
    @DisplayName("Given Order Status Message When Send Call Template")
    public void givenOrderStatusMessageWhenSendCallTemplate(){

        Order order = defaultOrder().withStatus(OrderStatus.RECEIVED)
                                    .build();

        OrderLog orderLog = defaultOrderLog().build();
        order.addLog(orderLog);

        OrderItem item = defaultOrderItem().build();
        order.addItem(item);

        PaymentMethod paymentMethod = defaultPaymentMethod().build();
        order.addPaymentMethod(paymentMethod);

        producer.send(order);

        Consumer<String, SpecificRecord> consumer = createConsumer();

        ConsumerRecord<String, SpecificRecord> singleRecord = KafkaTestUtils.getSingleRecord(consumer, topic);

        assertThat(singleRecord).isNotNull();
        assertThat(singleRecord.key()).isEqualTo(order.getId().toString());
        assertThat(singleRecord.value()).isNotNull();
    }


    private Consumer<String, SpecificRecord> createConsumer() {
        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps(OrderStatusProducerTest.class.getSimpleName(), "true", embeddedKafkaBroker);
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class.getName());
        consumerProps.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, "true");
        consumerProps.put(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "mock://http://localhost:8081");
        Consumer<String, SpecificRecord> consumer = new DefaultKafkaConsumerFactory<String, SpecificRecord>(consumerProps).createConsumer();
        consumer.subscribe(Collections.singleton(topic));
        return consumer;
    }
}

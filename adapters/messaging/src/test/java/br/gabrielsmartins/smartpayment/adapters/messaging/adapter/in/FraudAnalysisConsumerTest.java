package br.gabrielsmartins.smartpayment.adapters.messaging.adapter.in;


import br.gabrielsmartins.schemas.fraud_detected.FraudDetected;
import br.gabrielsmartins.schemas.fraud_detected.Item;
import br.gabrielsmartins.schemas.fraud_detected.PaymentMethod;
import br.gabrielsmartins.schemas.fraud_detected.PaymentType;
import br.gabrielsmartins.schemas.fraud_discarded.FraudDiscarded;
import br.gabrielsmartins.smartpayment.adapters.messaging.config.TopicProperties;
import br.gabrielsmartins.smartpayment.application.ports.in.ProcessFraudAnalysisUseCase;
import br.gabrielsmartins.smartpayment.application.ports.in.SubmitOrderUseCase;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.waitAtMost;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@EmbeddedKafka(partitions = 1, controlledShutdown = true)
@ActiveProfiles("test")
@MockBeans({
        @MockBean(SubmitOrderUseCase.class)
})
public class FraudAnalysisConsumerTest {

    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;

    @InjectMocks
    private FraudAnalysisConsumer consumer;

    @MockBean
    private ProcessFraudAnalysisUseCase useCase;

    @Autowired
    private TopicProperties topicProperties;

    private AutoCloseable closeable;

    private String topic;

    @BeforeEach
    public void setup(){
        this.closeable = MockitoAnnotations.openMocks(this);
        this.topic = this.topicProperties.getInputTopic(TopicProperties.FRAUD_ANALYZED);
    }

    @Test
    @DisplayName("Given Fraud Detected Message When Receive Then Process Fraud Analysis")
    public void givenFraudDetectedWhenReceiveThenProcessFraudAnalysis(){

        var message = FraudDetected.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setOrderId(12345L)
                .setCustomerId(UUID.randomUUID().toString())
                .setCreatedAt(LocalDateTime.now())
                .setValidatedAt(LocalDateTime.now())
                .setFraud(true)
                .setTotalAmount(BigDecimal.valueOf(1500))
                .setTotalDiscount(BigDecimal.ZERO)
                .setItems(List.of(Item.newBuilder()
                                       .setProductId(UUID.randomUUID().toString())
                                       .setQuantity(1)
                                       .setAmount(BigDecimal.valueOf(1500))
                                       .build()))
                .setPaymentMethods(List.of(PaymentMethod.newBuilder()
                                                    .setAmount(BigDecimal.valueOf(1500))
                                                    .setPaymentType(PaymentType.CASH)
                                                    .build()))
                .build();

        Producer<String, SpecificRecord> producer = createProducer();

        producer.send(new ProducerRecord<>(topic, UUID.randomUUID().toString(), message));
        producer.flush();
        producer.close();

        waitAtMost(20, TimeUnit.SECONDS).untilAsserted(() -> {
            verify(useCase, times(1)).process(anyLong(), anyBoolean());
        });
    }

    @Test
    @DisplayName("Given Fraud Discarded Message When Receive Then Process Fraud Analysis")
    public void givenFraudDiscardedWhenReceiveThenProcessFraudAnalysis(){

        var message = FraudDiscarded.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setOrderId(12345L)
                .setCustomerId(UUID.randomUUID().toString())
                .setCreatedAt(LocalDateTime.now())
                .setValidatedAt(LocalDateTime.now())
                .setFraud(true)
                .setTotalAmount(BigDecimal.valueOf(1500))
                .setTotalDiscount(BigDecimal.ZERO)
                .setItems(List.of(br.gabrielsmartins.schemas.fraud_discarded.Item.newBuilder()
                                                                            .setProductId(UUID.randomUUID().toString())
                                                                            .setQuantity(1)
                                                                            .setAmount(BigDecimal.valueOf(1500))
                                                                            .build()))
                .setPaymentMethods(List.of(br.gabrielsmartins.schemas.fraud_discarded.PaymentMethod.newBuilder()
                                                                                    .setAmount(BigDecimal.valueOf(1500))
                                                                                    .setPaymentType(br.gabrielsmartins.schemas.fraud_discarded.PaymentType.CASH)
                                                                                    .build()))
                .build();

        Producer<String, SpecificRecord> producer = createProducer();

        producer.send(new ProducerRecord<>(topic, UUID.randomUUID().toString(), message));
        producer.flush();
        producer.close();

        waitAtMost(20, TimeUnit.SECONDS).untilAsserted(() -> {
            verify(useCase, times(1)).process(anyLong(), anyBoolean());
        });
    }

    private Producer<String, SpecificRecord> createProducer() {
        Map<String, Object> producerProps = KafkaTestUtils.producerProps(embeddedKafkaBroker);
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
        producerProps.put(KafkaAvroSerializerConfig.AUTO_REGISTER_SCHEMAS, "true");
        producerProps.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "mock://http://localhost:8081");
        return new DefaultKafkaProducerFactory<String, SpecificRecord>(producerProps).createProducer();
    }


    @AfterEach
    public void releaseMocks() throws Exception {
        closeable.close();
    }
}

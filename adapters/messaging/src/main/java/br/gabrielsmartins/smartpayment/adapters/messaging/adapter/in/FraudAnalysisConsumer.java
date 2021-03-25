package br.gabrielsmartins.smartpayment.adapters.messaging.adapter.in;


import br.gabrielsmartins.schemas.fraud_detected.FraudDetected;
import br.gabrielsmartins.schemas.fraud_discarded.FraudDiscarded;
import br.gabrielsmartins.smartpayment.application.ports.in.ProcessFraudAnalysisUseCase;
import br.gabrielsmartins.smartpayment.common.stereotype.MessagingAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;

@MessagingAdapter
@KafkaListener(topics = {"${topic.input.fraud-analyzed}"})
@Slf4j
@RequiredArgsConstructor
public class FraudAnalysisConsumer {

    private final ProcessFraudAnalysisUseCase useCase;

    @KafkaHandler
    public void consume(@Headers MessageHeaders headers, @Payload FraudDetected message){
        log.info("Receiving fraud analysis: {},{}", headers, message);
        useCase.process(message.getOrderId(), message.getFraud());
        log.info("Fraud analysis processed successfully: {}", message);
    }

    @KafkaHandler
    public void consume(@Headers MessageHeaders headers, @Payload FraudDiscarded message){
        log.info("Receiving fraud analysis: {},{}", headers, message);
        useCase.process(message.getOrderId(), message.getFraud());
        log.info("Fraud analysis processed successfully: {}", message);
    }

    @KafkaHandler(isDefault = true)
    public void consume(Object object){
        log.warn("Unrecognized message: {}", object);
    }

}

package br.gabrielsmartins.smartpayment.adapters.messaging.adapter.in.frauds;


import br.gabrielsmartins.smartpayment.common.stereotype.MessagingAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;

@MessagingAdapter
@KafkaListener(topics = {"${topic.input.order-requested}"})
@Slf4j
@RequiredArgsConstructor
public class FraudAnalysisConsumer {


}

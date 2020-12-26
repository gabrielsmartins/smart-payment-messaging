package br.gabrielsmartins.smartpayment.adapters.messaging.adapter.out;

import br.gabrielsmartins.smartpayment.adapters.messaging.mapper.out.OrderStatusMapper;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.ports.out.SendOrderStatusPort;
import br.gabrielsmartins.smartpayment.common.stereotype.MessagingAdapter;
import lombok.RequiredArgsConstructor;
import org.apache.avro.specific.SpecificRecord;
import org.springframework.kafka.core.KafkaTemplate;

@MessagingAdapter
@RequiredArgsConstructor
public class OrderStatusProducer implements SendOrderStatusPort {

    private final KafkaTemplate<String, SpecificRecord> template;
    private final OrderStatusMapper mapper;

    @Override
    public void send(Order order) {

    }
}

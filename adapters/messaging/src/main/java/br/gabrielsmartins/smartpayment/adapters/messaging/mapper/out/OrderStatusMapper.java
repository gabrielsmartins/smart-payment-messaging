package br.gabrielsmartins.smartpayment.adapters.messaging.mapper.out;

import br.gabrielsmartins.smartpayment.application.domain.Order;
import org.apache.avro.specific.SpecificRecord;
import org.springframework.stereotype.Component;

@Component
public interface OrderStatusMapper<T extends SpecificRecord> {

    T mapToMessage(Order order);

}

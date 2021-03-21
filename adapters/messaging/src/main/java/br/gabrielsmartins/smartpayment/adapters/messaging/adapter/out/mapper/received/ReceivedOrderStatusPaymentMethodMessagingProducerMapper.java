package br.gabrielsmartins.smartpayment.adapters.messaging.adapter.out.mapper.received;

import br.gabrielsmartins.schemas.order_received.PaymentMethod;
import br.gabrielsmartins.schemas.order_received.PaymentType;
import org.springframework.stereotype.Component;

@Component
public class ReceivedOrderStatusPaymentMethodMessagingProducerMapper {

    public PaymentMethod mapToMessage(br.gabrielsmartins.smartpayment.application.domain.PaymentMethod paymentMethod){
        return PaymentMethod.newBuilder()
                            .setAmount(paymentMethod.getAmount())
                            .setPaymentType(PaymentType.valueOf(paymentMethod.getPaymentType().name()))
                            .build();
    }
}

package br.gabrielsmartins.smartpayment.adapters.messaging.adapter.out.mapper.completed;

import br.gabrielsmartins.schemas.order_completed.PaymentMethod;
import br.gabrielsmartins.schemas.order_completed.PaymentType;
import org.springframework.stereotype.Component;

@Component
public class CompletedOrderStatusPaymentMethodMessagingProducerMapper {

    public PaymentMethod mapToMessage(br.gabrielsmartins.smartpayment.application.domain.PaymentMethod paymentMethod){
        return PaymentMethod.newBuilder()
                            .setAmount(paymentMethod.getAmount())
                            .setPaymentType(PaymentType.valueOf(paymentMethod.getPaymentType().name()))
                            .build();
    }
}

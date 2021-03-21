package br.gabrielsmartins.smartpayment.adapters.messaging.adapter.out.mapper.validated;

import br.gabrielsmartins.schemas.order_validated.PaymentMethod;
import br.gabrielsmartins.schemas.order_validated.PaymentType;
import org.springframework.stereotype.Component;

@Component
public class ValidatedOrderStatusPaymentMethodMessagingProducerMapper {

    public PaymentMethod mapToMessage(br.gabrielsmartins.smartpayment.application.domain.PaymentMethod paymentMethod){
        return PaymentMethod.newBuilder()
                            .setAmount(paymentMethod.getAmount())
                            .setPaymentType(PaymentType.valueOf(paymentMethod.getPaymentType().name()))
                            .build();
    }
}

package br.gabrielsmartins.smartpayment.adapters.messaging.adapter.out.mapper.confirmed;

import br.gabrielsmartins.schemas.order_confirmed.PaymentMethod;
import br.gabrielsmartins.schemas.order_confirmed.PaymentType;
import org.springframework.stereotype.Component;

@Component
public class ConfirmedOrderStatusPaymentMethodMessagingProducerMapper {

    public PaymentMethod mapToMessage(br.gabrielsmartins.smartpayment.application.domain.PaymentMethod paymentMethod){
        return PaymentMethod.newBuilder()
                            .setAmount(paymentMethod.getAmount())
                            .setPaymentType(PaymentType.valueOf(paymentMethod.getPaymentType().name()))
                            .build();
    }
}

package br.gabrielsmartins.smartpayment.adapters.messaging.adapter.out.mapper.rejected;

import br.gabrielsmartins.schemas.order_rejected.PaymentMethod;
import br.gabrielsmartins.schemas.order_rejected.PaymentType;
import org.springframework.stereotype.Component;

@Component
public class RejectedOrderStatusPaymentMethodMessagingProducerMapper {

    public PaymentMethod mapToMessage(br.gabrielsmartins.smartpayment.application.domain.PaymentMethod paymentMethod){
        return PaymentMethod.newBuilder()
                            .setAmount(paymentMethod.getAmount())
                            .setPaymentType(PaymentType.valueOf(paymentMethod.getPaymentType().name()))
                            .build();
    }
}

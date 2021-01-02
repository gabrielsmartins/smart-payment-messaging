package br.gabrielsmartins.smartpayment.adapters.messaging.mapper.out.rejected;

import br.gabrielsmartins.schemas.order_rejected.PaymentMethod;
import br.gabrielsmartins.schemas.order_rejected.PaymentType;
import org.springframework.stereotype.Component;

@Component
public class RejectedOrderStatusPaymentMethodMessagingOutputMapper {

    public PaymentMethod mapToMessage(br.gabrielsmartins.smartpayment.application.domain.PaymentMethod paymentMethod){
        return PaymentMethod.newBuilder()
                            .setAmount(paymentMethod.getAmount())
                            .setPaymentType(PaymentType.valueOf(paymentMethod.getPaymentType().name()))
                            .build();
    }
}

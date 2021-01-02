package br.gabrielsmartins.smartpayment.adapters.messaging.mapper.in;


import br.gabrielsmartins.smartpayment.application.domain.PaymentMethod;
import br.gabrielsmartins.smartpayment.application.domain.enums.PaymentType;
import org.springframework.stereotype.Component;

@Component
public class PaymentMethodMessagingInputMapper {

    public PaymentMethod mapToDomain(br.gabrielsmartins.schemas.new_order.PaymentMethod paymentMethodMessage){
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setAmount(paymentMethodMessage.getAmount());
        paymentMethod.setPaymentType(PaymentType.fromDescription(paymentMethodMessage.getPaymentType().name()));
        return paymentMethod;
    }
}

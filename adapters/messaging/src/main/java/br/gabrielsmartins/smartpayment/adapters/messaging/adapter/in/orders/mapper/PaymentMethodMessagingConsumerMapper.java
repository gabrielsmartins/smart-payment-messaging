package br.gabrielsmartins.smartpayment.adapters.messaging.adapter.in.orders.mapper;


import br.gabrielsmartins.smartpayment.application.domain.PaymentMethod;
import br.gabrielsmartins.smartpayment.application.domain.enums.PaymentType;
import org.springframework.stereotype.Component;

@Component
public class PaymentMethodMessagingConsumerMapper {

    public PaymentMethod mapToDomain(br.gabrielsmartins.schemas.order_requested.PaymentMethod paymentMethodMessage){
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setAmount(paymentMethodMessage.getAmount());
        paymentMethod.setPaymentType(PaymentType.fromDescription(paymentMethodMessage.getPaymentType().name()));
        return paymentMethod;
    }
}

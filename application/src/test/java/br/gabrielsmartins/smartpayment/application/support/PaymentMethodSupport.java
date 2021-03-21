package br.gabrielsmartins.smartpayment.application.support;

import br.gabrielsmartins.smartpayment.application.domain.PaymentMethod;
import br.gabrielsmartins.smartpayment.application.domain.enums.PaymentType;

import java.math.BigDecimal;

public class PaymentMethodSupport {

    private PaymentMethodSupport(){
        super();
    }

    public static PaymentMethod.PaymentMethodBuilder defaultPaymentMethod(){
        return PaymentMethod.builder()
                .withPaymentType(PaymentType.CASH)
                .withAmount(BigDecimal.TEN);
    }
}

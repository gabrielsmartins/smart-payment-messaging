package br.gabrielsmartins.smartpayment.adapters.persistence.support;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.PaymentMethodEntity;
import br.gabrielsmartins.smartpayment.application.domain.enums.PaymentType;

import java.math.BigDecimal;

public class PaymentMethodEntitySupport {

    private PaymentMethodEntitySupport(){
        super();
    }

    public static PaymentMethodEntity.PaymentMethodEntityBuilder defaultPaymentMethodEntity(){
        return PaymentMethodEntity.builder()
                .withPaymentType(PaymentType.CASH.getDescription())
                .withAmount(BigDecimal.TEN);
    }
}

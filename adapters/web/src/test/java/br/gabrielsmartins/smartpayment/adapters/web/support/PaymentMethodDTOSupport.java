package br.gabrielsmartins.smartpayment.adapters.web.support;

import br.gabrielsmartins.smartpayment.adapters.web.adapter.in.dto.PaymentMethodDTO;
import br.gabrielsmartins.smartpayment.application.domain.enums.PaymentType;

import java.math.BigDecimal;

public class PaymentMethodDTOSupport {

    private PaymentMethodDTOSupport(){
        super();
    }

    public static PaymentMethodDTO.PaymentMethodDTOBuilder defaultPaymentMethodDto(){
        return PaymentMethodDTO.builder()
                                .withPaymentType(PaymentType.CASH.getDescription())
                                .withAmount(BigDecimal.TEN);
    }
}

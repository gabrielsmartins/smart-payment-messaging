package br.gabrielsmartins.smartpayment.adapters.web.enums;

import br.gabrielsmartins.smartpayment.domain.enums.PaymentType;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum PaymentTypeDTO {

    CREDIT_CARD("CREDIT CARD"),
    CASH("CASH"),
    INTERNET_BANKING("INTERNET BANKING"),
    PAYPAL("PAYPAL");

    private String description;

    PaymentTypeDTO(String description) {
        this.description = description;
    }

    public static PaymentType fromDescription(String paymentTypeDescription) {
        return Stream.of(PaymentType.values())
                .filter(paymentType -> paymentType.getDescription().equalsIgnoreCase(paymentTypeDescription))
                .findFirst()
                .orElse(null);
    }

    @JsonValue
    public String getDescription() {
        return description;
    }
}

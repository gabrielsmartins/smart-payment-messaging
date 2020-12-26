package br.gabrielsmartins.smartpayment.application.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {

    NEW("NEW"),
    REQUESTED("REQUESTED"),
    VALIDATED("VALIDATED"),
    REJECTED("REJECTED"),
    CONFIRMED("CONFIRMED"),
    COMPLETED("COMPLETED");

    private final String description;

}

package br.gabrielsmartins.smartpayment.application.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {

    NEW("NEW"),
    RECEIVED("RECEIVED"),
    STARTED("STARTED"),
    REPLIED("REPLIED"),
    FINISHED("FINISHED");

    private final String description;

}

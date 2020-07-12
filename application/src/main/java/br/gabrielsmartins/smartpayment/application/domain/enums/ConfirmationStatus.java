package br.gabrielsmartins.smartpayment.application.domain.enums;

public enum ConfirmationStatus {

    RECEIVED("RECEIVED"),
    STARTED("STARTED"),
    ACCEPTED("ACCEPTED"),
    REJECTED("REJECTED");

    private String description;

    ConfirmationStatus(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

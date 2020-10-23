package br.gabrielsmartins.smartpayment.application.exception;

public class IllegalStateOrderException extends RuntimeException {

    public IllegalStateOrderException(String message) {
        super(message);
    }
}

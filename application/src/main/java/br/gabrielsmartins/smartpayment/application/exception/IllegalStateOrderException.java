package br.gabrielsmartins.smartpayment.application.exception;

public class IllegalStateOrderException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public IllegalStateOrderException(String message) {
        super(message);
    }
}

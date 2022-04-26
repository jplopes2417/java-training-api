package br.com.training.exception.bet;

public class BetConfigurationNotFoundException extends RuntimeException {
    public BetConfigurationNotFoundException(String message) {
        super(message);
    }

    public BetConfigurationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

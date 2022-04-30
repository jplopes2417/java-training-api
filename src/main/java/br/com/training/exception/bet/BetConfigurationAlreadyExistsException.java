package br.com.training.exception.bet;

public class BetConfigurationAlreadyExistsException extends RuntimeException {
    public BetConfigurationAlreadyExistsException(String message) {
        super(message);
    }

    public BetConfigurationAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}

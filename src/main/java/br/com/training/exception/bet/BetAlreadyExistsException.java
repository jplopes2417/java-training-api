package br.com.training.exception.bet;

public class BetAlreadyExistsException extends RuntimeException {
    public BetAlreadyExistsException(String message) {
        super(message);
    }

    public BetAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}

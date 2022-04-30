package br.com.training.exception.bet;

public class InvalidBetException extends RuntimeException {
    public InvalidBetException(String message) {
        super(message);
    }

    public InvalidBetException(String message, Throwable cause) {
        super(message, cause);
    }
}

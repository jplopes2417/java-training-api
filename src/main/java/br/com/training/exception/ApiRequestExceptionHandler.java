package br.com.training.exception;

import br.com.training.exception.bet.*;
import br.com.training.exception.user.UserApiRequestException;
import br.com.training.exception.user.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ApiRequestExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(value = {UserApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(UserApiRequestException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ApiException apiException = new ApiException(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiException, badRequest);

    }

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<Object> userNotFoundException(UserNotFoundException e) {
        HttpStatus notFound = HttpStatus.NOT_FOUND;

        ApiException apiException = new ApiException(
                e.getMessage(),
                notFound,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiException, notFound);
    }

    @ExceptionHandler(value = {BetAlreadyExistsException.class})
    public ResponseEntity<Object> betAlreadyExistsException(BetAlreadyExistsException e) {
        HttpStatus notFound = HttpStatus.BAD_REQUEST;

        ApiException apiException = new ApiException(
                e.getMessage(),
                notFound,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiException, notFound);
    }

    @ExceptionHandler(value = {BetConfigurationAlreadyExistsException.class})
    public ResponseEntity<Object> betConfigurationAlreadyExistsException(BetConfigurationAlreadyExistsException e) {
        HttpStatus notFound = HttpStatus.BAD_REQUEST;

        ApiException apiException = new ApiException(
                e.getMessage(),
                notFound,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiException, notFound);
    }

    @ExceptionHandler(value = {BetNotFoundException.class})
    public ResponseEntity<Object> betNotFoundException(BetNotFoundException e) {
        HttpStatus notFound = HttpStatus.NOT_FOUND;

        ApiException apiException = new ApiException(
                e.getMessage(),
                notFound,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiException, notFound);
    }

    @ExceptionHandler(value = {BetConfigurationNotFoundException.class})
    public ResponseEntity<Object> betConfigurationNotFoundException(BetConfigurationNotFoundException e) {
        HttpStatus notFound = HttpStatus.NOT_FOUND;

        ApiException apiException = new ApiException(
                e.getMessage(),
                notFound,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiException, notFound);
    }

    @ExceptionHandler(value = {InvalidBetException.class})
    public ResponseEntity<Object> invalidBetException(InvalidBetException e) {
        HttpStatus notFound = HttpStatus.BAD_REQUEST;

        ApiException apiException = new ApiException(
                e.getMessage(),
                notFound,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiException, notFound);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid (MethodArgumentNotValidException ex){

        HttpStatus status = HttpStatus.BAD_REQUEST;

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        String erros = errors.keySet().stream()
                .map(key -> key + "=" + errors.get(key))
                .collect(Collectors.joining(", ", "{", "}"));

        log.error("Erros encontrados: " + erros);

        ApiException apiException = new ApiException(
                erros,
                status,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiException, status);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleGeneralError(Exception e) {
        HttpStatus notFound = HttpStatus.INTERNAL_SERVER_ERROR;

        ApiException apiException = new ApiException(
                e.getMessage(),
                notFound,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiException, notFound);
    }
}

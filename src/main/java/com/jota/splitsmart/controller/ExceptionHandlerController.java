package com.jota.splitsmart.controller;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.jota.splitsmart.exception.FriendAlreadyAddedException;
import com.jota.splitsmart.exception.UserAlreadyRegisteredException;
import com.jota.splitsmart.exception.UserNotFoundException;
import com.jota.splitsmart.exchangedata.ErrorDTO;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorDTO badRequest(final MethodArgumentNotValidException methodArgumentNotValidException) {
        log.info(methodArgumentNotValidException.getMessage());
        return ErrorDTO.builder()
            .message(buildMessage(methodArgumentNotValidException))
            .status(BAD_REQUEST.value())
            .build();
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorDTO userNotFound(final UserNotFoundException userNotFoundException) {
        log.error(userNotFoundException.getMessage());
        return ErrorDTO.builder()
            .message(userNotFoundException.getMessage())
            .status(NOT_FOUND.value())
            .build();
    }

    @ExceptionHandler(UserAlreadyRegisteredException.class)
    @ResponseStatus(CONFLICT)
    public ErrorDTO userAlreadyRegistered(final UserAlreadyRegisteredException userAlreadyRegisteredException) {
        log.error(userAlreadyRegisteredException.getMessage());
        return ErrorDTO.builder()
            .message(userAlreadyRegisteredException.getMessage())
            .status(CONFLICT.value())
            .build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorDTO constraintException(ConstraintViolationException e) {
        log.info(e.getMessage());
        return ErrorDTO.builder()
            .message(e.getMessage())
            .status(BAD_REQUEST.value())
            .build();
    }

    @ExceptionHandler(FriendAlreadyAddedException.class)
    @ResponseStatus(CONFLICT)
    public ErrorDTO contactAlreadyFriend(final FriendAlreadyAddedException FriendAlreadyAddedException) {
        log.error(FriendAlreadyAddedException.getMessage());
        return ErrorDTO.builder()
            .message(FriendAlreadyAddedException.getMessage())
            .status(CONFLICT.value())
            .build();
    }

    private String buildMessage(final MethodArgumentNotValidException methodArgumentNotValidException) {
        return format(
            "'%s' %s",
            methodArgumentNotValidException.getFieldError().getField(),
            methodArgumentNotValidException.getFieldError().getDefaultMessage());
    }
}

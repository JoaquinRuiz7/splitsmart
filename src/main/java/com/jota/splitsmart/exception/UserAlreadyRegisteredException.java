package com.jota.splitsmart.exception;

public class UserAlreadyRegisteredException extends RuntimeException {

    public UserAlreadyRegisteredException(final String message) {
        super(message);
    }
}

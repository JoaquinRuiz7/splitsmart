package com.jota.splitsmart.exception;

public class ExpenseNotFoundException extends RuntimeException {

    public ExpenseNotFoundException(final String message) {
        super(message);
    }

}

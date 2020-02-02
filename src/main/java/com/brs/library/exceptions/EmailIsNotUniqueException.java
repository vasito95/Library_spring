package com.brs.library.exceptions;

public class EmailIsNotUniqueException extends Exception {
    public EmailIsNotUniqueException(String message) {
        super(message);
    }
}

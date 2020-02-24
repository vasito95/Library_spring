package com.brs.library.exceptions;

public class BookNameNotUnique extends RuntimeException {
    public BookNameNotUnique(String message) {
        super(message);
    }
}

package com.escouto.digitallibrary.domain.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String message) {
        super(message);
    }
}
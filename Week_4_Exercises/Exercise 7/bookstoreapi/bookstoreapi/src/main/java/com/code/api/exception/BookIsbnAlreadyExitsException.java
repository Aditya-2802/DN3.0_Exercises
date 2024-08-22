package com.code.api.exception;



public class BookIsbnAlreadyExitsException extends RuntimeException{

    public BookIsbnAlreadyExitsException() {
    }

    public BookIsbnAlreadyExitsException(String message) {
        super(message);
    }
}

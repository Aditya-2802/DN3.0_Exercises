package com.code.api.exception;



public class BookIsbnAlreadyExitsException extends RuntimeException{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BookIsbnAlreadyExitsException() {
    }

    public BookIsbnAlreadyExitsException(String message) {
        super(message);
    }
}

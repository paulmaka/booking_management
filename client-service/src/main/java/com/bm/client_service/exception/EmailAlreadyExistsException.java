package com.bm.client_service.exception;

public class EmailAlreadyExistsException extends RuntimeException{

    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}

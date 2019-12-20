package com.herval.food.infrastructure.service.email;

/*
 * Criado Por Herval Mata em 19/12/2019
 */
public class EmailException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailException(String message) {
        super(message);
    }
}

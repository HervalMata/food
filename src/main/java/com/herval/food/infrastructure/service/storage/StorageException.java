package com.herval.food.infrastructure.service.storage;

/*
 * Criado Por Herval Mata em 18/12/2019
 */
public class StorageException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }

    public StorageException(String message) {
        super(message);
    }
}

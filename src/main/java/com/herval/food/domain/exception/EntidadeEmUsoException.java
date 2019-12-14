package com.herval.food.domain.exception;

/*
 * Criado Por Herval Mata em 14/12/2019
 */
public class EntidadeEmUsoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EntidadeEmUsoException(String mensagem) {
        super(mensagem);
    }
}

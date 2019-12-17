package com.herval.food.domain.exception;

/*
 * Criado Por Herval Mata em 17/12/2019
 */
public class PermissaoNaoEncontradoException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public PermissaoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public PermissaoNaoEncontradoException(Long permissaoId) {
        this(String.format("Não existe um cadastro de permissão com código %d", permissaoId));
    }
}

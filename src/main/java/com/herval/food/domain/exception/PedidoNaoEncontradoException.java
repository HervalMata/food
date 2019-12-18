package com.herval.food.domain.exception;

/*
 * Criado Por Herval Mata em 17/12/2019
 */
public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public PedidoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public PedidoNaoEncontradoException(Long pedidoId) {
        this(String.format("Não existe um pedido com código %d", pedidoId));
    }
}

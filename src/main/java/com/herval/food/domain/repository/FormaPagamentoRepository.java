package com.herval.food.domain.repository;

import com.herval.food.domain.model.FormaPagamento;

import java.util.List;

/*
 * Criado Por Herval Mata em 14/12/2019
 */
public interface FormaPagamentoRepository {

    List<FormaPagamento> listar();
    FormaPagamento buscar(Long id);
    FormaPagamento salvar(FormaPagamento formaPagamento);
    void remover(FormaPagamento formaPagamento);
}

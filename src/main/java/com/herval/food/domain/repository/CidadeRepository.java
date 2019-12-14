package com.herval.food.domain.repository;

import com.herval.food.domain.model.Cidade;

import java.util.List;

/*
 * Criado Por Herval Mata em 14/12/2019
 */
public interface CidadeRepository {

    List<Cidade> listar();
    Cidade buscar(Long id);
    Cidade salvar(Cidade cidade);
    void remover(Cidade cidade);
}

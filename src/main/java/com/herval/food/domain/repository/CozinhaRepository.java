package com.herval.food.domain.repository;

import com.herval.food.domain.model.Cozinha;

import java.util.List;

/*
 * Criado Por Herval Mata em 14/12/2019
 */
public interface CozinhaRepository {

    List<Cozinha> listar();
    Cozinha buscar(Long id);
    Cozinha salvar(Cozinha cozinha);
    void remover(Long id);
}

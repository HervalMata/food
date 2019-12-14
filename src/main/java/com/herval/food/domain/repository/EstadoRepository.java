package com.herval.food.domain.repository;

import com.herval.food.domain.model.Estado;

import java.util.List;

/*
 * Criado Por Herval Mata em 14/12/2019
 */
public interface EstadoRepository {

    List<Estado> listar();
    Estado buscar(Long id);
    Estado salvar(Estado estado);
    void remover(Long id);
}

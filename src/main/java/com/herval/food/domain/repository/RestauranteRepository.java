package com.herval.food.domain.repository;

import com.herval.food.domain.model.Restaurante;

import java.util.List;

/*
 * Criado Por Herval Mata em 14/12/2019
 */
public interface RestauranteRepository {

    List<Restaurante> listar();
    Restaurante buscar(Long id);
    Restaurante salvar(Restaurante restaurante);
    void remover(Restaurante restaurante);
}

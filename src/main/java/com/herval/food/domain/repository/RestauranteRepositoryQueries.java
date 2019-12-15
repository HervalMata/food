package com.herval.food.domain.repository;

import com.herval.food.domain.model.Restaurante;

import java.math.BigDecimal;
import java.util.List;

/*
 * Criado Por Herval Mata em 14/12/2019
 */
public interface RestauranteRepositoryQueries {
    List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);
    List<Restaurante> findComFreteGratis(String nome);
}

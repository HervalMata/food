package com.herval.food.domain.repository;

import com.herval.food.domain.model.FotoProduto;

/*
 * Criado Por Herval Mata em 18/12/2019
 */
public interface ProdutoRepositoryQueries {

    FotoProduto save(FotoProduto fotoProduto);
    void delete(FotoProduto fotoProduto);
}

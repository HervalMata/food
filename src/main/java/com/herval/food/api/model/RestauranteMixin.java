package com.herval.food.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.herval.food.domain.model.Cozinha;
import com.herval.food.domain.model.Endereco;
import com.herval.food.domain.model.FormaPagamento;
import com.herval.food.domain.model.Produto;

import java.time.LocalDateTime;
import java.util.List;

/*
 * Criado Por Herval Mata em 16/12/2019
 */
public abstract class RestauranteMixin {

    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Cozinha cozinha;

    @JsonIgnore
    private Endereco endereco;

    @JsonIgnore
    private LocalDateTime dataCadastro;

    @JsonIgnore
    private LocalDateTime dataAtualizacao;

    @JsonIgnore
    private List<FormaPagamento> formasPagamento;

    @JsonIgnore
    private List<Produto> produtos;
}

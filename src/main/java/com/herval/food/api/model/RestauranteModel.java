package com.herval.food.api.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.herval.food.api.model.view.RestauranteView;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/*
 * Criado Por Herval Mata em 16/12/2019
 */
@Setter
@Getter
public class RestauranteModel {

    @JsonView({ RestauranteView.Resumo.class, RestauranteView.ApenasNome.class })
    private Long id;

    @JsonView({ RestauranteView.Resumo.class, RestauranteView.ApenasNome.class })
    private String nome;

    @JsonView({ RestauranteView.Resumo.class })
    private BigDecimal taxaFrete;

    @JsonView({ RestauranteView.Resumo.class })
    private CozinhaModel cozinha;

    private Boolean ativo;
    private Boolean aberto;
    private EnderecoModel endereco;
}

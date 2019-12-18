package com.herval.food.api.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/*
 * Criado Por Herval Mata em 16/12/2019
 */
@Setter
@Getter
public class RestauranteModel {

    private Long id;
    private String nome;
    private BigDecimal taxaFrete;
    private CozinhaModel cozinha;
    private Boolean ativo;
    private Boolean aberto;
    private EnderecoModel endereco;
}

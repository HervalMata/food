package com.herval.food.api.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/*
 * Criado Por Herval Mata em 17/12/2019
 */
@Setter
@Getter
public class ProdutoModel {

    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Boolean ativo;
}

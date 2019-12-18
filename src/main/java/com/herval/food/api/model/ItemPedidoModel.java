package com.herval.food.api.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/*
 * Criado Por Herval Mata em 17/12/2019
 */
@Setter
@Getter
public class ItemPedidoModel {

    private Long produtoId;
    private String produtoNome;
    private Integer quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal precoTotal;
    private String observacao;
}

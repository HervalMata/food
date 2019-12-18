package com.herval.food.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

/*
 * Criado Por Herval Mata em 17/12/2019
 */
@Setter
@Getter
public class ItemPedidoInput {

    @NotNull
    private Long produtoId;

    @NotNull
    @PositiveOrZero
    private Integer quantidade;

    private String observacao;
}

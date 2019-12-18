package com.herval.food.domain.model;

import java.util.Arrays;
import java.util.List;

/*
 * Criado Por Herval Mata em 15/12/2019
 */
public enum StatusPedido {

    CRIADO("Criado"),
    CONFIRMADO("Confirmado", CRIADO),
    ENTREGUE("Entregue", CONFIRMADO),
    CANCELADO("Cancelado", CRIADO);

    private String descricao;
    private List<StatusPedido> statusAnteriores;

    StatusPedido(String descricao, StatusPedido... statuaAnteriores) {
        this.descricao = descricao;
        this.statusAnteriores = Arrays.asList(statuaAnteriores);
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean naoPodeAlterarPara(StatusPedido novoStatus) {
        return !novoStatus.statusAnteriores.contains(this);
    }
}

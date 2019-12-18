package com.herval.food.api.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

/*
 * Criado Por Herval Mata em 17/12/2019
 */
@Setter
@Getter
public class PedidoResumoModel {

    private Long id;
    private BigDecimal subtotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    private String status;
    private OffsetDateTime dataCriacao;
    private RestauranteResumoModel restaurante;
    private UsuarioModel cliente;
}

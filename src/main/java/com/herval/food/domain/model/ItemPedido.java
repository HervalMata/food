package com.herval.food.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

/*
 * Criado Por Herval Mata em 15/12/2019
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class ItemPedido {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal precoUnitario;
    private BigDecimal precoTotal;
    private Integer quantidade;
    private String observacao;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Produto produto;
}

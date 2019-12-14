package com.herval.food.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/*
 * Criado Por Herval Mata em 14/12/2019
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class FormaPagamento {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;
}

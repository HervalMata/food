package com.herval.food.api.model;

import com.herval.food.api.model.EstadoModel;
import lombok.Getter;
import lombok.Setter;

/*
 * Criado Por Herval Mata em 16/12/2019
 */
@Setter
@Getter
public class CidadeModel {

    private Long id;
    private String nome;
    private EstadoModel estado;
}

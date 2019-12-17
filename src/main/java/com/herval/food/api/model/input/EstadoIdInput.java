package com.herval.food.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/*
 * Criado Por Herval Mata em 16/12/2019
 */
@Setter
@Getter
public class EstadoIdInput {

    @NotNull
    private Long id;
}

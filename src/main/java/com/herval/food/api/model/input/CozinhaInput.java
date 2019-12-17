package com.herval.food.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/*
 * Criado Por Herval Mata em 16/12/2019
 */
@Setter
@Getter
public class CozinhaInput {

    @NotBlank
    private String nome;
}

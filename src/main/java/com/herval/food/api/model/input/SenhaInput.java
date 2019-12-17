package com.herval.food.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/*
 * Criado Por Herval Mata em 17/12/2019
 */
@Setter
@Getter
public class SenhaInput {

    @NotBlank
    private String senhaAtual;

    @NotBlank
    private String novaSenha;
}

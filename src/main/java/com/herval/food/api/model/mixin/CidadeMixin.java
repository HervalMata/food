package com.herval.food.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.herval.food.domain.model.Estado;

/*
 * Criado Por Herval Mata em 16/12/2019
 */
public abstract class CidadeMixin {

    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Estado estado;
}

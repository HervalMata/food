package com.herval.food.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.herval.food.domain.model.Restaurante;

import java.util.List;

/*
 * Criado Por Herval Mata em 16/12/2019
 */
public abstract class CozinhaMixin {

    @JsonIgnore
    private List<Restaurante> restaurantes;
}

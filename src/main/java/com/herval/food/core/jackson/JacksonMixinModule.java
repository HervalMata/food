package com.herval.food.core.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.herval.food.api.model.mixin.CidadeMixin;
import com.herval.food.api.model.mixin.CozinhaMixin;
import com.herval.food.api.model.RestauranteMixin;
import com.herval.food.domain.model.Cidade;
import com.herval.food.domain.model.Cozinha;
import com.herval.food.domain.model.Restaurante;
import org.springframework.stereotype.Component;

/*
 * Criado Por Herval Mata em 16/12/2019
 */
@Component
public class JacksonMixinModule extends SimpleModule {

    private static final long serialVersionUID = 1L;

    public JacksonMixinModule() {
        setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
        setMixInAnnotation(Cidade.class, CidadeMixin.class);
        setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
    }
}

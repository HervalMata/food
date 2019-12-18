package com.herval.food.api.assembler;

import com.herval.food.api.model.input.RestauranteInput;
import com.herval.food.domain.model.Cidade;
import com.herval.food.domain.model.Cozinha;
import com.herval.food.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
 * Criado Por Herval Mata em 16/12/2019
 */
@Component
public class RestauranteInputDisassembler {
    @Autowired
    private ModelMapper modelMapper;

    public Restaurante toDomainObject(RestauranteInput restauranteInput) {
        return modelMapper.map(restauranteInput, Restaurante.class);
    }

    public void copyToDomainObject(RestauranteInput restauranteInput, Restaurante restaurante) {
        restaurante.setCozinha(new Cozinha());
        if (restaurante.getEndereco() != null) {
            restaurante.getEndereco().setCidade(new Cidade());
        }
        modelMapper.map(restauranteInput, restaurante);
    }
}

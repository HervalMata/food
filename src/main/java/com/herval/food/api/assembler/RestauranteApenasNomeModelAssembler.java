package com.herval.food.api.assembler;

import com.herval.food.api.FoodLinks;
import com.herval.food.api.controller.RestauranteController;
import com.herval.food.api.model.RestauranteApenasNomeModel;
import com.herval.food.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

/*
 * Criado Por Herval Mata em 16/12/2019
 */
@Component
public class RestauranteApenasNomeModelAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteApenasNomeModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FoodLinks foodLinks;

    public RestauranteApenasNomeModelAssembler() {
        super(RestauranteController.class, RestauranteApenasNomeModel.class);
    }

    @Override
    public RestauranteApenasNomeModel toModel(Restaurante restaurante) {
        RestauranteApenasNomeModel restauranteApenasNomeModel = createModelWithId(
                restaurante.getId(), restaurante);
        modelMapper.map(restaurante, restauranteApenasNomeModel);
        restauranteApenasNomeModel.add(foodLinks.linkToRestaurantes("restaurantes"));

        return restauranteApenasNomeModel;
    }

    @Override
    public CollectionModel<RestauranteApenasNomeModel> toCollectionModel(Iterable<? extends Restaurante> entities) {
        return super.toCollectionModel(entities)
                .add(foodLinks.linkToRestaurantes());
    }
}

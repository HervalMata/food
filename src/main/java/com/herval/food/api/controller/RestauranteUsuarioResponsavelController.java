package com.herval.food.api.controller;

import com.herval.food.api.FoodLinks;
import com.herval.food.api.assembler.UsuarioModelAssembler;
import com.herval.food.api.model.UsuarioModel;
import com.herval.food.api.openapi.controller.RestauranteUsuarioResponsavelControllerOpenApi;
import com.herval.food.domain.model.Restaurante;
import com.herval.food.domain.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
 * Criado Por Herval Mata em 17/12/2019
 */
@RestController
@RequestMapping(path = "/restaurantes/{restauranteId}/responsaveis", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteUsuarioResponsavelController implements RestauranteUsuarioResponsavelControllerOpenApi {

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;

    @Autowired
    private FoodLinks foodLinks;

    @Override
    @GetMapping
    public CollectionModel<UsuarioModel> listar(@PathVariable Long restauranteId) {
        Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);
        CollectionModel<UsuarioModel> usuarioModels =
                usuarioModelAssembler.toCollectionModel(restaurante.getResponsaveis())
                            .removeLinks()
                .add(foodLinks.linkToResponsaveisRestaurante(restauranteId))
                .add(foodLinks.linkToResponsaveisRestauranteAssociacao(restauranteId, "associar"));

        usuarioModels.getContent().stream().forEach(usuarioModel -> {
            usuarioModel.add(foodLinks.linkToResponsaveisRestauranteDesassociacao(
                    restauranteId, usuarioModel.getId(), "desassociar"
            ));
        });
        return usuarioModels;
    }

    @Override
    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> desassociar(@PathVariable Long restauranteId,
                                            @PathVariable Long usuarioId) {
        restauranteService.desassociarResponsavel(restauranteId, usuarioId);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PutMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associar(@PathVariable Long restauranteId,
                                         @PathVariable Long usuarioId) {
        restauranteService.associarResponsavel(restauranteId, usuarioId);
        return ResponseEntity.noContent().build();
    }
}

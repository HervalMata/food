package com.herval.food.api.controller;

import com.herval.food.api.FoodLinks;
import com.herval.food.api.assembler.FormaPagamentoModelAssembler;
import com.herval.food.api.model.FormaPagamentoModel;
import com.herval.food.api.openapi.controller.RestauranteFormaPagamentoControllerOpenApi;
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
@RequestMapping(path = "/restaurantes/{restauranteId}/formaspagamento", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteFormaPagamentoController implements RestauranteFormaPagamentoControllerOpenApi {

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private FormaPagamentoModelAssembler formaPagamentoModelAssembler;

    @Autowired
    private FoodLinks foodLinks;

    @Override
    @GetMapping
    public CollectionModel<FormaPagamentoModel> listar(@PathVariable Long restauranteId) {
        Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);
        CollectionModel<FormaPagamentoModel> formaPagamentoModels =
                formaPagamentoModelAssembler.toCollectionModel(restaurante.getFormasPagamento())
                .removeLinks()
                .add(foodLinks.linkToRestauranteFormasPagamento(restauranteId))
                .add(foodLinks.linkToRestauranteFormasPagamentoAssociacao(restauranteId, "associar"));
        formaPagamentoModels.getContent().forEach(formaPagamentoModel -> {
            formaPagamentoModel.add(foodLinks.linkToRestauranteFormasPagamentoDesassociacao(
                    restauranteId, formaPagamentoModel.getId(), "desassociar"
            ));
        });
        return formaPagamentoModels;
    }

    @Override
    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> desassociar(@PathVariable Long restauranteId,
                                            @PathVariable Long formaPagamentoId) {
        restauranteService.desassociarFormaPagamento(restauranteId, formaPagamentoId);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PutMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associar(@PathVariable Long restauranteId,
                                         @PathVariable Long formaPagamentoId) {
        restauranteService.associarFormaPagamento(restauranteId, formaPagamentoId);
        return ResponseEntity.noContent().build();
    }
}

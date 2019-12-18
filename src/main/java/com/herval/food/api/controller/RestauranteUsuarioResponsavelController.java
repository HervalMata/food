package com.herval.food.api.controller;

import com.herval.food.api.assembler.UsuarioModelAssembler;
import com.herval.food.api.model.UsuarioModel;
import com.herval.food.domain.model.Restaurante;
import com.herval.food.domain.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * Criado Por Herval Mata em 17/12/2019
 */
@RestController
@RequestMapping("/restaurantes/{restauranteId}/responsaveis")
public class RestauranteUsuarioResponsavelController {

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;

    @GetMapping
    public List<UsuarioModel> listar(@PathVariable Long restauranteId) {
        Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);

        return usuarioModelAssembler.toCollectionModel(restaurante.getResponsaveis());
    }

    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long restauranteId,
                            @PathVariable Long usuarioId) {
        restauranteService.desassociarResponsavel(restauranteId, usuarioId);
    }

    @PutMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long restauranteId,
                         @PathVariable Long usuarioId) {
        restauranteService.associarResponsavel(restauranteId, usuarioId);
    }
}

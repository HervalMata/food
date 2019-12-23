package com.herval.food.api.openapi.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.herval.food.api.exceptionhandler.Problema;
import com.herval.food.api.model.RestauranteApenasNomeModel;
import com.herval.food.api.model.RestauranteBasicoModel;
import com.herval.food.api.model.RestauranteModel;
import com.herval.food.api.model.input.RestauranteInput;
import com.herval.food.api.model.view.RestauranteView;
import com.herval.food.api.openapi.model.RestauranteBasicoModeloOpenApi;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

/*
 * Criado Por Herval Mata em 19/12/2019
 */
@Api(tags = "Restaurantes")
public interface RestauranteControllerOpenApi {

    @ApiOperation(value = "Lista restaurantes", response = RestauranteBasicoModeloOpenApi.class)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "Nome da projeção de pedidos", allowableValues = "apenas-nome",
                             name = "projecao", paramType = "query", type = "string")
    })
    @JsonView(RestauranteView.class)
    CollectionModel<RestauranteBasicoModel> listar();

    @ApiOperation(value = "Listar restaurantes", hidden = true)
    CollectionModel<RestauranteApenasNomeModel> listarApenasNomes();

    @ApiOperation("Busca um restaurante por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do restaurante inválido", response = Problema.class),
            @ApiResponse(code = 404, message = "Restaurante não encontrada", response = Problema.class)
    })
    RestauranteModel buscar(
            @ApiParam(value = "ID de um restaurante", example = "1", required = true)
                    Long restauranteId);

    @ApiOperation("Cadastra um restaurante")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Restaurante cadastrado")
    })
    RestauranteModel adicionar(
            @ApiParam(name = "corpo", value = "Representação de um novo restaurante")
                    RestauranteInput restauranteInput);

    @ApiOperation("Atualiza um restaurante por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Restaurante atualizado"),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problema.class)
    })
    RestauranteModel atualizar(
            @ApiParam(value = "ID de um restaurante", example = "1", required = true)
                    Long restauranteId,
            @ApiParam(name = "corpo", value = "Representação de um restaurante com os novos dados")
                    RestauranteInput restauranteInput);

    @ApiOperation("Ativa um restaurante por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Restaurante ativado com sucesso"),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problema.class)
    })
    ResponseEntity<Void> ativar(
            @ApiParam(value = "ID de um restaurante", example = "1", required = true)
                    Long restauranteId);

    @ApiOperation("Inativa um restaurante por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Restaurante inativado com sucesso"),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problema.class)
    })
    ResponseEntity<Void> inativar(
            @ApiParam(value = "ID de um restaurante", example = "1", required = true)
                    Long restauranteId);

    @ApiOperation("Ativa multiplos restaurante")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Restaurantes ativados com sucesso")
    })
    void ativarMultiplos(
            @ApiParam(name = "corpo", value = "IDs dos restaurante", example = "1", required = true)
                    List<Long> restauranteIds);

    @ApiOperation("Inativa multiplos restaurante")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Restaurantes inativados com sucesso")
    })
    void inativarMultiplos(
            @ApiParam(name = "corpo", value = "IDs dos restaurante", example = "1", required = true)
                    List<Long> restauranteIds);

    @ApiOperation("Abre um restaurante Por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Restaurante aberto com sucesso"),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problema.class)
    })
    ResponseEntity<Void> abrir(
            @ApiParam(value = "ID do restaurante", example = "1", required = true)
                    Long restauranteId);

    @ApiOperation("Fecha um restaurante Por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Restaurante fechado com sucesso"),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problema.class)
    })
    ResponseEntity<Void> fechar(
            @ApiParam(value = "ID do restaurante", example = "1", required = true)
                    Long restauranteId);
}

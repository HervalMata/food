package com.herval.food.api.openapi.controller;

import com.herval.food.api.exceptionhandler.Problema;
import com.herval.food.api.model.CozinhaModel;
import com.herval.food.api.model.input.CozinhaInput;
import io.swagger.annotations.*;

import java.util.List;

/*
 * Criado Por Herval Mata em 19/12/2019
 */
@Api(tags = "Cozinhas")
public interface CozinhaControllerOpenApi {

    @ApiOperation("Lista de cozinhas")
    List<CozinhaModel> listar();

    @ApiOperation("Busca uma cozinha por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID da cozinha inválido", response = Problema.class),
            @ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problema.class)
    })
    CozinhaModel buscar(
            @ApiParam(value = "ID de uma cozinha", example = "1", required = true)
                    Long cozinhaId);

    @ApiOperation("Cadastra uma cozinha")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cozinha cadastrada")
    })
    CozinhaModel adicionar(
            @ApiParam(name = "corpo", value = "Representação de uma nova cozinha")
                    CozinhaInput cozinhaInput);

    @ApiOperation("Atualiza uma cozinha por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cozinha atualizada"),
            @ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problema.class)
    })
    CozinhaModel atualizar(
            @ApiParam(value = "ID de uma cozinha", example = "1", required = true)
                    Long cozinhaId,
            @ApiParam(name = "corpo", value = "Representação de uma cozinha com os novos dados")
                    CozinhaInput cozinhaInput);

    @ApiOperation("Exclui uma cozinha por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Cozinha excluida"),
            @ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problema.class)
    })
    void remover(
            @ApiParam(value = "ID de uma cozinha", example = "1", required = true)
                    Long cozinhaId);
}

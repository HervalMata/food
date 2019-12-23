package com.herval.food.api.openapi.controller;

import com.herval.food.api.exceptionhandler.Problema;
import com.herval.food.api.model.EstadoModel;
import com.herval.food.api.model.input.EstadoInput;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;

/*
 * Criado Por Herval Mata em 19/12/2019
 */
@Api(tags = "Estados")
public interface EstadoControllerOpenApi {

    @ApiOperation("Lista de estados")
    CollectionModel<EstadoModel> listar();

    @ApiOperation("Busca um estado por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID de estado inválido", response = Problema.class),
            @ApiResponse(code = 404, message = "Estado não encontrado", response = Problema.class)
    })
    EstadoModel buscar(
            @ApiParam(value = "ID de um estado", example = "1", required = true)
                    Long estadoId);

    @ApiOperation("Cadastra um estado")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Estado cadastrado")
    })
    EstadoModel adicionar(
            @ApiParam(name = "corpo", value = "Representação de um nova estado")
                    EstadoInput estadoInput);

    @ApiOperation("Atualiza um estado por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Estado atualizado"),
            @ApiResponse(code = 404, message = "Estado não encontrado", response = Problema.class)
    })
    EstadoModel atualizar(
            @ApiParam(value = "ID de um estado", example = "1", required = true)
                    Long estadoId,
            @ApiParam(name = "corpo", value = "Representação de um estado com os novos dados")
                    EstadoInput estadoInput);

    @ApiOperation("Exclui um estado por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Estado excluido"),
            @ApiResponse(code = 404, message = "Estado não encontrado", response = Problema.class)
    })
    void remover(
            @ApiParam(value = "ID de um estado", example = "1", required = true)
                    Long estadoId);
}

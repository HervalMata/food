package com.herval.food.api.openapi.controller;

import com.herval.food.api.exceptionhandler.Problema;
import com.herval.food.api.model.GrupoModel;
import com.herval.food.api.model.input.GrupoInput;
import io.swagger.annotations.*;

import java.util.List;

/*
 * Criado Por Herval Mata em 19/12/2019
 */
@Api(tags = "Grupos")
public interface GrupoControllerOpenApi {

    @ApiOperation("Lista de grupos")
    List<GrupoModel> listar();

    @ApiOperation("Busca um grupo por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID da grupo inválido", response = Problema.class),
            @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problema.class)
    })
    GrupoModel buscar(
            @ApiParam(value = "ID de um grupo", example = "1", required = true)
                    Long grupoId);

    @ApiOperation("Cadastra um grupo")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Grupo cadastrado")
    })
    GrupoModel adicionar(
            @ApiParam(name = "corpo", value = "Representação de um novo grupo")
                    GrupoInput grupoInput);

    @ApiOperation("Atualiza um grupo por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Grupo atualizado"),
            @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problema.class)
    })
    GrupoModel atualizar(
            @ApiParam(value = "ID de um grupo", example = "1", required = true)
                    Long grupoId,
            @ApiParam(name = "corpo", value = "Representação de um grupo com os novos dados")
                    GrupoInput grupoInput);

    @ApiOperation("Exclui um grupo por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Grupo excluido"),
            @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problema.class)
    })
    void remover(
            @ApiParam(value = "ID de um grupo", example = "1", required = true)
                    Long grupoId);
}

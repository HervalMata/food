package com.herval.food.api.openapi.controller;

import com.herval.food.api.exceptionhandler.Problema;
import com.herval.food.api.model.CidadeModel;
import com.herval.food.api.model.input.CidadeInput;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;

/*
 * Criado Por Herval Mata em 19/12/2019
 */
@Api(tags = "Cidades")
public interface CidadeControllerOpenApi {

    @ApiOperation("Lista de cidades")
    CollectionModel<CidadeModel> listar();

    @ApiOperation("Busca uma cidade por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID da cidade inválido", response = Problema.class),
            @ApiResponse(code = 404, message = "Cidade não encontrada", response = Problema.class)
    })
    CidadeModel buscar(
            @ApiParam(value = "ID de uma cidade", example = "1", required = true)
            Long cidadeId);

    @ApiOperation("Cadastra uma cidade")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cidade cadastrada")
    })
    CidadeModel adicionar(
            @ApiParam(name = "corpo", value = "Representação de uma nova cidade")
            CidadeInput cidadeInput);

    @ApiOperation("Atualiza uma cidade por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cidade atualizada"),
            @ApiResponse(code = 404, message = "Cidade não encontrada", response = Problema.class)
    })
    CidadeModel atualizar(
            @ApiParam(value = "ID de uma cidade", example = "1", required = true)
                    Long cidadeId,
            @ApiParam(name = "corpo", value = "Representação de uma cidade com os novos dados")
                    CidadeInput cidadeInput);

    @ApiOperation("Exclui uma cidade por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Cidade excluida"),
            @ApiResponse(code = 404, message = "Cidade não encontrada", response = Problema.class)
    })
    void remover(
            @ApiParam(value = "ID de uma cidade", example = "1", required = true)
                    Long cidadeId);
}

package com.herval.food.api.openapi.controller;

import com.herval.food.api.exceptionhandler.Problema;
import com.herval.food.api.model.ProdutoModel;
import com.herval.food.api.model.RestauranteModel;
import com.herval.food.api.model.input.ProdutoInput;
import io.swagger.annotations.*;

import java.util.List;

/*
 * Criado Por Herval Mata em 19/12/2019
 */
@Api(tags = "Produtos")
public interface RestauranteProdutoControllerOpenApi {

    @ApiOperation(value = "Lista os produtos de um restaurante")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID da restaurante inválido", response = Problema.class),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problema.class)
    })
    List<ProdutoModel> listar(
            @ApiParam(value = "ID do restaurante", example = "1", required = true)
                    Long restauranteId,

                    @ApiParam(value = "Indica se deve ou não incluir produtos inativos no resultado da listagem",
                            example = "false", defaultValue = "false")
                            boolean incluirInativos);


    @ApiOperation("Busca um produto de um restaurante")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do restaurante ou produto inválido", response = Problema.class),
            @ApiResponse(code = 404, message = "Produto de restaurante não encontrada", response = Problema.class)
    })
    ProdutoModel buscar(
            @ApiParam(value = "ID do restaurante", example = "1", required = true)
                    Long restauranteId,
            @ApiParam(value = "ID do produto", example = "1", required = true)
                    Long produtoId);

    @ApiOperation("Cadastra um produto de um restaurante")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Produto cadastrado"),
            @ApiResponse(code = 404, message = "Restaurante não encontrada", response = Problema.class)
    })
    ProdutoModel adicionar(
            @ApiParam(value = "ID do restaurante", example = "1", required = true)
                    Long restauranteId,
            @ApiParam(name = "corpo", value = "Representação de um novo produto")
                    ProdutoInput produtoInput);

    @ApiOperation("Atualiza um produto de um restaurante")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Produto atualizado"),
            @ApiResponse(code = 404, message = "Produto de restaurante não encontrado", response = Problema.class)
    })
    ProdutoModel atualizar(
            @ApiParam(value = "ID de um restaurante", example = "1", required = true)
                    Long restauranteId,
            @ApiParam(value = "ID do produto", example = "1", required = true)
                    Long produtoId,
            @ApiParam(name = "corpo", value = "Representação de um restaurante com os novos dados")
                    ProdutoInput produtoInput);
}

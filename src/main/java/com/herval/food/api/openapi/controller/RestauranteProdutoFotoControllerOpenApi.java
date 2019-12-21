package com.herval.food.api.openapi.controller;

import com.herval.food.api.exceptionhandler.Problema;
import com.herval.food.api.model.FotoProdutoModel;
import com.herval.food.api.model.input.FotoProdutoInput;
import io.swagger.annotations.*;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/*
 * Criado Por Herval Mata em 21/12/2019
 */
@Api(tags = "Produtos")
public interface RestauranteProdutoFotoControllerOpenApi {

    @ApiOperation("Atualiza a foto do produto de um restaurante")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Foto do produto atualizado"),
            @ApiResponse(code = 404, message = "Produto de restaurante não encontrado", response = Problema.class)
    })
    FotoProdutoModel atualizarFoto(
            @ApiParam(value = "ID de um restaurante", example = "1", required = true)
                    Long restauranteId,
            @ApiParam(value = "ID do produto", example = "1", required = true)
                    Long produtoId,
            FotoProdutoInput fotoProdutoInput,
            @ApiParam(value = "Arquivo da foto do produto (máximo 500KB, apenas JPG e PNG)",
                    required = true)
            MultipartFile arquivo) throws IOException;

    @ApiOperation("Exclui a foto do produto de um restaurante")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Foto do produto excluido"),
            @ApiResponse(code = 400, message = "ID do restaurante inválido", response = Problema.class),
            @ApiResponse(code = 404, message = "Foto de produto não encontrado", response = Problema.class)
    })
    void remover(
            @ApiParam(value = "ID do restaurante", example = "1", required = true)
                    Long restauranteId,
            @ApiParam(value = "ID do produto", example = "1", required = true)
                    Long produtoId);

    @ApiOperation(value = "Busca a foto do produto de um restaurante",
                    produces = "application/json, image/jpeg, image/png")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do restaurante ou produto inválido", response = Problema.class),
            @ApiResponse(code = 404, message = "Foto de produto não encontrado", response = Problema.class)
    })
    FotoProdutoModel buscar(
            @ApiParam(value = "ID do restaurante", example = "1", required = true)
                    Long restauranteId,
            @ApiParam(value = "ID do produto", example = "1", required = true)
                    Long produtoId);

    @ApiOperation(value = "Busca a foto do produto de um restaurante", hidden = true)
    ResponseEntity<InputStreamResource> servirFoto(Long restauranteId, Long produtoId, String acceptHeader) throws HttpMediaTypeNotAcceptableException;
}

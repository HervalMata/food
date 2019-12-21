package com.herval.food.api.openapi.controller;

import com.herval.food.api.exceptionhandler.Problema;
import com.herval.food.api.model.PermissaoModel;
import io.swagger.annotations.*;

import java.util.List;

/*
 * Criado Por Herval Mata em 21/12/2019
 */
@Api(tags = "Grupos")
public interface GrupoPermissaoControllerOpenApi {

    @ApiOperation(value = "Lista as formas de pagamento associadas ao restaurante")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do grupo inválido", response = Problema.class),
            @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problema.class)
    })
    List<PermissaoModel> listar(
            @ApiParam(value = "ID do grupo", example = "1", required = true)
                    Long grupoId);

    @ApiOperation("Associação de permissão com grupo")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Associação realizada com sucesso", response = Problema.class),
            @ApiResponse(code = 404, message = "Grupo ou permissão não encontrado", response = Problema.class)
    })
    void associar(
            @ApiParam(value = "ID do grupo", example = "1", required = true)
                    Long grupoId,
            @ApiParam(value = "ID da permissao", example = "1", required = true)
                    Long permissaoId);

    @ApiOperation("Desassociação de permissão com grupo")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Desassociação realizada com sucesso", response = Problema.class),
            @ApiResponse(code = 404, message = "Grupo ou permissão não encontrado", response = Problema.class)
    })
    void desassociar(
            @ApiParam(value = "ID do grupo", example = "1", required = true)
                    Long grupoId,
            @ApiParam(value = "ID da permissao", example = "1", required = true)
                    Long permissaoId);
}

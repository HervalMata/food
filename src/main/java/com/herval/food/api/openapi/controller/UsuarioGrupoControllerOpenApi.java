package com.herval.food.api.openapi.controller;

import com.herval.food.api.exceptionhandler.Problema;
import com.herval.food.api.model.GrupoModel;
import io.swagger.annotations.*;

import java.util.List;

/*
 * Criado Por Herval Mata em 21/12/2019
 */
@Api(tags = "Usuarios")
public interface UsuarioGrupoControllerOpenApi {

    @ApiOperation(value = "Lista os grupos associados a um usuário")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Usuário não encontrado", response = Problema.class)
    })
    List<GrupoModel> listar(
            @ApiParam(value = "ID do usuário", example = "1", required = true)
                    Long usuarioId);

    @ApiOperation("Associação de usuário com grupo")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Associação realizada com sucesso", response = Problema.class),
            @ApiResponse(code = 404, message = "Grupo ou usuário não encontrado", response = Problema.class)
    })
    void associar(
            @ApiParam(value = "ID do grupo", example = "1", required = true)
                    Long grupoId,
            @ApiParam(value = "ID do usuário", example = "1", required = true)
                    Long usuarioId);

    @ApiOperation("Desassociação de usuário com grupo")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Desassociação realizada com sucesso", response = Problema.class),
            @ApiResponse(code = 404, message = "Grupo ou usuário não encontrado", response = Problema.class)
    })
    void desassociar(
            @ApiParam(value = "ID do grupo", example = "1", required = true)
                    Long grupoId,
            @ApiParam(value = "ID do usuário", example = "1", required = true)
                    Long usuarioId);
}

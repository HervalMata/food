package com.herval.food.api.openapi.controller;

import com.herval.food.api.exceptionhandler.Problema;
import com.herval.food.api.model.FormaPagamentoModel;
import com.herval.food.api.model.UsuarioModel;
import io.swagger.annotations.*;

import java.util.List;

/*
 * Criado Por Herval Mata em 21/12/2019
 */
@Api(tags = "Restaurantes")
public interface RestauranteUsuarioResponsavelControllerOpenApi {

    @ApiOperation(value = "Lista os usuários associados ao restaurante")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problema.class)
    })
    List<UsuarioModel> listar(
            @ApiParam(value = "ID do restaurante", example = "1", required = true)
                    Long restauranteId);

    @ApiOperation("Associação de restaurante com o usuário responsável")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Associação realizada com sucesso", response = Problema.class),
            @ApiResponse(code = 404, message = "Restaurante ou responsável não encontrado", response = Problema.class)
    })
    void associar(
            @ApiParam(value = "ID do restaurante", example = "1", required = true)
                    Long restauranteId,
            @ApiParam(value = "ID do usuário", example = "1", required = true)
                    Long usuarioId);

    @ApiOperation("Desassociação de restaurante com usuário responsável")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Desassociação realizada com sucesso", response = Problema.class),
            @ApiResponse(code = 404, message = "Restaurante ou usuário não encontrado", response = Problema.class)
    })
    void desassociar(
            @ApiParam(value = "ID do restaurante", example = "1", required = true)
                    Long restauranteId,
            @ApiParam(value = "ID do usuário", example = "1", required = true)
                    Long usuarioId);
}

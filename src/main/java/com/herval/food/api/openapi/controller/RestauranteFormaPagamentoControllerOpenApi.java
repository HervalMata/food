package com.herval.food.api.openapi.controller;

import com.herval.food.api.exceptionhandler.Problema;
import com.herval.food.api.model.FormaPagamentoModel;
import io.swagger.annotations.*;

import java.util.List;

/*
 * Criado Por Herval Mata em 21/12/2019
 */
@Api(tags = "Restaurantes")
public interface RestauranteFormaPagamentoControllerOpenApi {

    @ApiOperation(value = "Lista as formas de pagamento associadas ao restaurante")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problema.class)
    })
    List<FormaPagamentoModel> listar(
            @ApiParam(value = "ID do restaurante", example = "1", required = true)
                      Long restauranteId);

    @ApiOperation("Associação de restaurante com forma de pagamento")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Associação realizada com sucesso", response = Problema.class),
            @ApiResponse(code = 404, message = "Restaurante ou forma de pagamento não encontrado", response = Problema.class)
    })
    void associar(
            @ApiParam(value = "ID do restaurante", example = "1", required = true)
                    Long restauranteId,
            @ApiParam(value = "ID da forma de pagamento", example = "1", required = true)
                    Long formaPagamentoId);

    @ApiOperation("Desassociação de restaurante com forma de pagamento")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Desassociação realizada com sucesso", response = Problema.class),
            @ApiResponse(code = 404, message = "Restaurante ou forma de pagamento não encontrado", response = Problema.class)
    })
    void desassociar(
            @ApiParam(value = "ID do restaurante", example = "1", required = true)
                    Long restauranteId,
            @ApiParam(value = "ID da forma de pagamento", example = "1", required = true)
                    Long formaPagamentoId);
}

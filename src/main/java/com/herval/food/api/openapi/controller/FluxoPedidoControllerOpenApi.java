package com.herval.food.api.openapi.controller;

import com.herval.food.api.exceptionhandler.Problema;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;

/*
 * Criado Por Herval Mata em 20/12/2019
 */
@Api(tags = "Pedidos")
public interface FluxoPedidoControllerOpenApi {

    @ApiOperation("Confirmação de pedido")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Pedido confirmado com sucesso"),
            @ApiResponse(code = 404, message = "Pedido não encontrado", response = Problema.class)
    })
    ResponseEntity<Void> confirmar(
            @ApiParam(value = "Código do pedido", example = "f9981ca4-5a5e-4da3-af04-933861df3e55", required = true)
                    String codigoPedido);

    @ApiOperation("Cancelamento de pedido")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Pedido cancelado com sucesso"),
            @ApiResponse(code = 404, message = "Pedido não encontrado", response = Problema.class)
    })
    ResponseEntity<Void> cancelar(
            @ApiParam(value = "Código do pedido", example = "f9981ca4-5a5e-4da3-af04-933861df3e55", required = true)
                    String codigoPedido);

    @ApiOperation("Registrar entrega de pedido")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Entrega pedido registrada com sucesso"),
            @ApiResponse(code = 404, message = "Pedido não encontrado", response = Problema.class)
    })
    ResponseEntity<Void> entregar(
            @ApiParam(value = "Código do pedido", example = "f9981ca4-5a5e-4da3-af04-933861df3e55", required = true)
                    String codigoPedido);
}

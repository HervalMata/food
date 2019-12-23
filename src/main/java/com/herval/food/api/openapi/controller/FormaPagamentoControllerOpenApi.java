package com.herval.food.api.openapi.controller;

import com.herval.food.api.exceptionhandler.Problema;
import com.herval.food.api.model.FormaPagamentoModel;
import com.herval.food.api.model.input.FormaPagamentoInput;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

/*
 * Criado Por Herval Mata em 19/12/2019
 */
@Api(tags = "formas de pagamento")
public interface FormaPagamentoControllerOpenApi {

    @ApiOperation("Lista as formas de pagamento")
    ResponseEntity<CollectionModel<FormaPagamentoModel>> listar(ServletWebRequest request);

    @ApiOperation("Busca uma forma de pagamento por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID da forma de pagamento inválido", response = Problema.class),
            @ApiResponse(code = 404, message = "Forma de pagamento não encontrada", response = Problema.class)
    })
    ResponseEntity<FormaPagamentoModel> buscar(
            @ApiParam(value = "ID de uma forma de pagamento", example = "1", required = true)
                    Long formaPagamentoId,
                    ServletWebRequest request);

    @ApiOperation("Cadastra uma forma de pagamento")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Forma de pagamento cadastrada")
    })
    FormaPagamentoModel adicionar(
            @ApiParam(name = "corpo", value = "Representação de uma nova forma de pagamento")
                    FormaPagamentoInput formaPagamentoInput);

    @ApiOperation("Atualiza uma forma de pagamento por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Forma de pagamento atualizada"),
            @ApiResponse(code = 404, message = "Forma de pagamento não encontrada", response = Problema.class)
    })
    FormaPagamentoModel atualizar(
            @ApiParam(value = "ID de uma forma de pagamento", example = "1", required = true)
                    Long formaPagamentoId,
            @ApiParam(name = "corpo", value = "Representação de uma forma de pagamento com os novos dados")
                    FormaPagamentoInput formaPagamentoInput);

    @ApiOperation("Exclui uma forma de pagamento por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Forma de pagamento excluida"),
            @ApiResponse(code = 404, message = "Forma de pagamento não encontrada", response = Problema.class)
    })
    void remover(
            @ApiParam(value = "ID de uma forma de pagamento", example = "1", required = true)
                    Long formaPagamentoId);
}

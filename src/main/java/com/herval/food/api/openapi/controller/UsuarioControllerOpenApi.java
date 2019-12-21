package com.herval.food.api.openapi.controller;

import com.herval.food.api.exceptionhandler.Problema;
import com.herval.food.api.model.UsuarioModel;
import com.herval.food.api.model.input.SenhaInput;
import com.herval.food.api.model.input.UsuarioInput;
import io.swagger.annotations.*;

import java.util.List;

/*
 * Criado Por Herval Mata em 19/12/2019
 */
@Api(tags = "Usuarios")
public interface UsuarioControllerOpenApi {

    @ApiOperation("Lista os usuários")
    List<UsuarioModel> listar();

    @ApiOperation("Busca um usuário por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do usuário inválido", response = Problema.class),
            @ApiResponse(code = 404, message = "Usuário não encontrado", response = Problema.class)
    })
    UsuarioModel buscar(
            @ApiParam(value = "ID do usuário", example = "1", required = true)
                    Long usuarioId);

    @ApiOperation("Cadastra um usuário")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Usuário cadastrado")
    })
    UsuarioModel adicionar(
            @ApiParam(name = "corpo", value = "Representação de um novo usuário")
                    UsuarioInput usuarioInput);

    @ApiOperation("Atualiza um usuário por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Usuário atualizado"),
            @ApiResponse(code = 404, message = "Uusário não encontrado", response = Problema.class)
    })
    UsuarioModel atualizar(
            @ApiParam(value = "ID do usuário", example = "1", required = true)
                    Long usuarioId,
            @ApiParam(name = "corpo", value = "Representação de um usuário com os novos dados")
                    UsuarioInput usuarioInput);

    @ApiOperation("Atualiza a senha de um usuário")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Senha alterada com sucesso"),
            @ApiResponse(code = 404, message = "Uusário não encontrado", response = Problema.class)
    })
    void alterarSenha(
            @ApiParam(value = "ID do usuário", example = "1", required = true)
                    Long usuarioId,
            @ApiParam(name = "corpo", value = "Representação de uma nova senha", required = true)
                    SenhaInput senhaInput);
}

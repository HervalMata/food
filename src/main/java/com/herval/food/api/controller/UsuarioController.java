package com.herval.food.api.controller;

import com.herval.food.api.assembler.UsuarioInputDisassembler;
import com.herval.food.api.assembler.UsuarioModelAssembler;
import com.herval.food.api.model.UsuarioModel;
import com.herval.food.api.model.input.SenhaInput;
import com.herval.food.api.model.input.UsuarioInput;
import com.herval.food.api.openapi.controller.UsuarioControllerOpenApi;
import com.herval.food.domain.model.Usuario;
import com.herval.food.domain.repository.UsuarioRepository;
import com.herval.food.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/*
 * Criado Por Herval Mata em 17/12/2019
 */
@RestController
@RequestMapping(path = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController implements UsuarioControllerOpenApi {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;

    @Autowired
    private UsuarioInputDisassembler usuarioInputDisassembler;

    @Override
    @GetMapping
    public CollectionModel<UsuarioModel> listar() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarioModelAssembler.toCollectionModel(usuarios);
    }

    @GetMapping("/{usuarioId}")
    public UsuarioModel buscar(@PathVariable Long usuarioId) {
        Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);
        return usuarioModelAssembler.toModel(usuario);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioModel adicionar(@RequestBody @Valid UsuarioInput usuarioInput) {

        Usuario usuario = usuarioInputDisassembler.toDomainObject(usuarioInput);
        usuario = usuarioService.salvar(usuario);
        return usuarioModelAssembler.toModel(usuario);


    }

    @PutMapping("/{usuarioId}")
    public UsuarioModel atualizar(@PathVariable Long usuarioId,
                                @RequestBody @Valid UsuarioInput usuarioInput) {

        Usuario usuarioAtual = usuarioService.buscarOuFalhar(usuarioId);
        usuarioInputDisassembler.copyToDomainObject(usuarioInput, usuarioAtual);
        usuarioAtual = usuarioService.salvar(usuarioAtual);
        return usuarioModelAssembler.toModel(usuarioAtual);

    }

    @PutMapping("/{usuarioId}/senha")
    public void alterarSenha(@PathVariable Long usuarioId,
                             @RequestBody @Valid SenhaInput senha) {
        usuarioService.alterarSenha(usuarioId, senha.getSenhaAtual(), senha.getNovaSenha());
    }
}

package com.herval.food.api.controller;

import com.herval.food.api.assembler.PermissaoModelAssembler;
import com.herval.food.api.model.PermissaoModel;
import com.herval.food.domain.model.Grupo;
import com.herval.food.domain.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * Criado Por Herval Mata em 17/12/2019
 */
@RestController
@RequestMapping("/grupos/{grupoId}/permissoes")
public class GrupoPermissaoController {

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private PermissaoModelAssembler permissaoModelAssembler;

    @GetMapping
    public List<PermissaoModel> listar(@PathVariable Long grupoId) {
        Grupo grupo = grupoService.buscarOuFalhar(grupoId);

        return permissaoModelAssembler.toCollectionModel(grupo.getPermissoes());
    }

    @DeleteMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long grupoId,
                            @PathVariable Long permissaoId) {
        grupoService.desassociarPermissao(grupoId, permissaoId);
    }

    @PutMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long grupoId,
                         @PathVariable Long permissaoId) {
        grupoService.associarPermissao(grupoId, permissaoId);
    }
}

package com.herval.food.api.controller;

import com.herval.food.api.assembler.GrupoInputDisassembler;
import com.herval.food.api.assembler.GrupoModelAssembler;
import com.herval.food.api.openapi.controller.GrupoControllerOpenApi;
import com.herval.food.api.model.GrupoModel;
import com.herval.food.api.model.input.GrupoInput;
import com.herval.food.domain.model.Grupo;
import com.herval.food.domain.repository.GrupoRepository;
import com.herval.food.domain.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/*
 * Criado Por Herval Mata em 17/12/2019
 */
@RestController
@RequestMapping(value = "/grupos")
public class GrupoController implements GrupoControllerOpenApi {

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private GrupoModelAssembler grupoModelAssembler;

    @Autowired
    private GrupoInputDisassembler grupoInputDisassembler;

    @GetMapping
    public List<GrupoModel> listar() {
        List<Grupo> grupos = grupoRepository.findAll();
        return grupoModelAssembler.toCollectionModel(grupos);
    }

    @GetMapping("/{grupoId}")
    public GrupoModel buscar(@PathVariable Long grupoId) {
        Grupo grupo = grupoService.buscarOuFalhar(grupoId);
        return grupoModelAssembler.toModel(grupo);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GrupoModel adicionar(@RequestBody @Valid GrupoInput grupoInput) {

        Grupo grupo = grupoInputDisassembler.toDomainObject(grupoInput);
        grupo = grupoService.salvar(grupo);
        return grupoModelAssembler.toModel(grupo);


    }

    @PutMapping("/{grupoId}")
    public GrupoModel atualizar(@PathVariable Long grupoId,
                                 @RequestBody @Valid GrupoInput grupoInput) {

        Grupo grupoAtual = grupoService.buscarOuFalhar(grupoId);
        grupoInputDisassembler.copyToDomainObject(grupoInput, grupoAtual);
        grupoAtual = grupoService.salvar(grupoAtual);
        return grupoModelAssembler.toModel(grupoAtual);

    }

    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long grupoId) {
        grupoService.excluir(grupoId);
    }
}

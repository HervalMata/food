package com.herval.food.api.controller;

import com.herval.food.api.assembler.EstadoInputDisassembler;
import com.herval.food.api.assembler.EstadoModelAssembler;
import com.herval.food.api.model.EstadoModel;
import com.herval.food.api.model.input.EstadoInput;
import com.herval.food.api.openapi.controller.EstadoControllerOpenApi;
import com.herval.food.domain.model.Estado;
import com.herval.food.domain.repository.EstadoRepository;
import com.herval.food.domain.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/*
 * Criado Por Herval Mata em 14/12/2019
 */
@RestController
@RequestMapping(path = "/estados", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstadoController implements EstadoControllerOpenApi {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private EstadoModelAssembler estadoModelAssembler;

    @Autowired
    EstadoInputDisassembler estadoInputDisassembler;

    @GetMapping
    public List<EstadoModel> listar() {
        List<Estado> estados = estadoRepository.findAll();
        return estadoModelAssembler.toCollectionModel(estados);
    }

    @GetMapping("/{estdoId}")
    public EstadoModel buscar(@PathVariable Long estadoId) {
        Estado estado = estadoService.buscarOuFalhar(estadoId);
        return estadoModelAssembler.toModel(estado);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoModel adicionar(@RequestBody @Valid EstadoInput estadoInput) {
        Estado estado = estadoInputDisassembler.toDomainObject(estadoInput);
        estado = estadoService.salvar(estado);
        return estadoModelAssembler.toModel(estado);
    }

    @PutMapping("/{estadoId}")
    public EstadoModel atualizar(@PathVariable Long estadoId,
                                       @RequestBody @Valid EstadoInput estadoInput) {
        Estado estadoAtual = estadoService.buscarOuFalhar(estadoId);
        estadoInputDisassembler.copyToDomainObject(estadoInput, estadoAtual);
        estadoAtual = estadoService.salvar(estadoAtual);
        return estadoModelAssembler.toModel(estadoAtual);

    }

    @DeleteMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long estadoId) {
        estadoService.excluir(estadoId);
    }
}

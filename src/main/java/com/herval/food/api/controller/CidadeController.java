package com.herval.food.api.controller;

import com.herval.food.api.assembler.CidadeInputDisassembler;
import com.herval.food.api.assembler.CidadeModelAssembler;
import com.herval.food.api.model.CidadeModel;
import com.herval.food.api.model.input.CidadeInput;
import com.herval.food.domain.exception.EstadoNaoEncontradoException;
import com.herval.food.domain.exception.NegocioException;
import com.herval.food.domain.model.Cidade;
import com.herval.food.domain.repository.CidadeRepository;
import com.herval.food.domain.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/*
 * Criado Por Herval Mata em 14/12/2019
 */
@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private CidadeModelAssembler cidadeModelAssembler;

    @Autowired
    private CidadeInputDisassembler cidadeInputDisassembler;

    @GetMapping
    public List<CidadeModel> listar() {
        List<Cidade> cidades = cidadeRepository.findAll();
        return cidadeModelAssembler.toCollectionModel(cidades);
    }

    @GetMapping("/{cidadeId}")
    public CidadeModel buscar(@PathVariable Long cidadeId) {
        Cidade cidade = cidadeService.buscarOuFalhar(cidadeId);
        return cidadeModelAssembler.toModel(cidade);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeModel adicionar(@RequestBody @Valid CidadeInput cidadeInput) {
        try {
            Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);
            cidade = cidadeService.salvar(cidade);
            return cidadeModelAssembler.toModel(cidade);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }

    }

    @PutMapping("/{cidadeId}")
    public CidadeModel atualizar(@PathVariable Long cidadeId,
                                             @RequestBody @Valid CidadeInput cidadeInput) {
           try {
               Cidade cidadeAtual = cidadeService.buscarOuFalhar(cidadeId);
               cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);
               cidadeAtual = cidadeService.salvar(cidadeAtual);
               return cidadeModelAssembler.toModel(cidadeAtual);
           } catch (EstadoNaoEncontradoException e) {
               throw new NegocioException(e.getMessage(), e);
           }

    }

    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cidadeId) {
            cidadeService.excluir(cidadeId);
    }
}

package com.herval.food.api.controller;

import com.herval.food.domain.exception.EntidadeEmUsoException;
import com.herval.food.domain.exception.EntidadeNaoEncontradaException;
import com.herval.food.domain.model.Cidade;
import com.herval.food.domain.repository.CidadeRepository;
import com.herval.food.domain.service.CidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping
    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    @GetMapping("/{cidadeId}")
    public ResponseEntity<Cidade> buscar(@PathVariable Long cidadeId) {
        Optional<Cidade> cidade = cidadeRepository.findById(cidadeId);

        if (cidade != null) {
            return ResponseEntity.ok(cidade.get());
        }

        return ResponseEntity
                .notFound()
                .build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade adicionar(@RequestBody Cidade cidade) {
        return cidadeService.salvar(cidade);
    }

    @PutMapping("/{cidadeId}")
    public ResponseEntity<?> atualizar(@PathVariable Long cidadeId,
                                             @RequestBody Cidade cidade) {
        try {
            Cidade cidadeAtual = cidadeRepository.findById(cidadeId).orElse(null);
            if (cidadeAtual != null) {
                BeanUtils.copyProperties(cidade, cidadeAtual, "id");
                cidadeAtual = cidadeService.salvar(cidadeAtual);
                return ResponseEntity.ok(cidadeAtual);
            }
            return ResponseEntity
                    .notFound()
                    .build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }


    }

    @DeleteMapping("/{cidadeId}")
    public ResponseEntity<Cidade> remover(@PathVariable Long cidadeId) {
        try {
            cidadeService.excluir(cidadeId);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}

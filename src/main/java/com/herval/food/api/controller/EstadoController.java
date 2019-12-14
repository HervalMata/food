package com.herval.food.api.controller;

import com.herval.food.domain.exception.EntidadeEmUsoException;
import com.herval.food.domain.exception.EntidadeNaoEncontradaException;
import com.herval.food.domain.model.Estado;
import com.herval.food.domain.repository.EstadoRepository;
import com.herval.food.domain.service.EstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * Criado Por Herval Mata em 14/12/2019
 */
@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private EstadoService estadoService;

    @GetMapping
    public List<Estado> listar() {
        return estadoRepository.listar();
    }

    @GetMapping("/{estdoId}")
    public ResponseEntity<Estado> buscar(@PathVariable Long estadoId) {
        Estado estado = estadoRepository.buscar(estadoId);

        if (estado != null) {
            return ResponseEntity.ok(estado);
        }

        return ResponseEntity
                .notFound()
                .build();
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Estado estado) {
        try {
            estado = estadoService.salvar(estado);

            return ResponseEntity.status(HttpStatus.CREATED).body(estado);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{estadoId}")
    public ResponseEntity<?> atualizar(@PathVariable Long estadoId,
                                       @RequestBody Estado estado) {
        try {
           Estado estadoAtual = estadoRepository.buscar(estadoId);

            if (estadoAtual != null) {
                BeanUtils.copyProperties(estado, estadoAtual, "id");
                estadoAtual = estadoService.salvar(estadoAtual);
                return ResponseEntity.ok(estadoAtual);
            }

            return ResponseEntity
                    .notFound()
                    .build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @DeleteMapping("/{estadoId}")
    public ResponseEntity<Estado> remover(@PathVariable Long estadoId) {
        try {
            estadoService.excluir(estadoId);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}

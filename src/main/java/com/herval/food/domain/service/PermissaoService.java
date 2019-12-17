package com.herval.food.domain.service;

import com.herval.food.domain.exception.GrupoNaoEncontradoException;
import com.herval.food.domain.exception.PermissaoNaoEncontradoException;
import com.herval.food.domain.model.Permissao;
import com.herval.food.domain.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * Criado Por Herval Mata em 17/12/2019
 */
@Service
public class PermissaoService {

    @Autowired
    private PermissaoRepository permissaoRepository;

    public Permissao buscarOuFalhar(Long permissaoId) {
        return permissaoRepository.findById(permissaoId).orElseThrow(() -> new PermissaoNaoEncontradoException(permissaoId));
    }
}

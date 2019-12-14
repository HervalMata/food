package com.herval.food.domain.repository;

import com.herval.food.domain.model.Permissao;

import java.util.List;

/*
 * Criado Por Herval Mata em 14/12/2019
 */
public interface PermissaoRepository {

    List<Permissao> listar();
    Permissao buscar(Long id);
    Permissao salvar(Permissao permissao);
    void remover(Permissao permissao);
}

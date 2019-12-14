package com.herval.food.infrastructure.repository;

import com.herval.food.domain.model.Permissao;
import com.herval.food.domain.repository.PermissaoRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/*
 * Criado Por Herval Mata em 14/12/2019
 */
@Component
public class PermissaoRepositoryImpl implements PermissaoRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Permissao> listar() {
        return manager.createQuery("from Permissao", Permissao.class)
                .getResultList();
    }

    @Override
    public Permissao buscar(Long id) {
        return manager.find(Permissao.class, id);
    }

    @Transactional
    @Override
    public Permissao salvar(Permissao permissao) {

        return manager.merge(permissao);
    }

    @Transactional
    @Override
    public void remover(Permissao permissao) {
        permissao = buscar(permissao.getId());
        manager.remove(permissao);
    }
}

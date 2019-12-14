package com.herval.food.infrastructure.repository;

import com.herval.food.domain.model.Cidade;
import com.herval.food.domain.repository.CidadeRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/*
 * Criado Por Herval Mata em 14/12/2019
 */
@Component
public class CidadeRepoisitoryImpl implements CidadeRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Cidade> listar() {
        return manager.createQuery("from Cidade", Cidade.class)
                .getResultList();
    }

    @Override
    public Cidade buscar(Long id) {
        return manager.find(Cidade.class, id);
    }

    @Transactional
    @Override
    public Cidade salvar(Cidade cidade) {

        return manager.merge(cidade);
    }

    @Transactional
    @Override
    public void remover(Long id) {
        Cidade cidade = buscar(id);
        if (cidade == null) {
            throw new EmptyResultDataAccessException(1);
        }
        manager.remove(cidade);
    }
}

package com.herval.food.jpa;

import com.herval.food.domain.model.Cozinha;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/*
 * Criado Por Herval Mata em 13/12/2019
 */
@Component
public class CadastroCozinha {

    @PersistenceContext
    private EntityManager manager;

    public List<Cozinha> listar() {

        return manager.createQuery("from Cozinha", Cozinha.class)
                .getResultList();
    }
}

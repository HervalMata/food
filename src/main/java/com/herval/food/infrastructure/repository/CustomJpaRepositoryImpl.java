package com.herval.food.infrastructure.repository;

import com.herval.food.domain.repository.CustomJpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.util.Optional;

/*
 * Criado Por Herval Mata em 14/12/2019
 */
public class CustomJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements CustomJpaRepository<T, ID> {

    private EntityManager manager;

    public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.manager = entityManager;
    }


    @Override
    public Optional<T> buscarPrimeiro() {
        var jpql = "from" + getDomainClass().getName();
        T entity = manager.createQuery(jpql, getDomainClass())
                .setMaxResults(1)
                .getSingleResult();
        return Optional.empty();
    }

    @Override
    public void detach(T entity) {
        manager.detach(entity);
    }
}

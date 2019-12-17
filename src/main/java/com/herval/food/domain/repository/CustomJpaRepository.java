package com.herval.food.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

/*
 * Criado Por Herval Mata em 14/12/2019
 */
@NoRepositoryBean
public interface CustomJpaRepository<T, ID> extends JpaRepository<T, ID> {
    Optional<T> buscarPrimeiro();
    void detach(T entity);
}

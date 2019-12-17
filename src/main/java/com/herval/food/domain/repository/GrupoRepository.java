package com.herval.food.domain.repository;

import com.herval.food.domain.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * Criado Por Herval Mata em 17/12/2019
 */
@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {
}

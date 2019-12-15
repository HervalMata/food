package com.herval.food.domain.repository;

import com.herval.food.domain.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * Criado Por Herval Mata em 14/12/2019
 */
@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}

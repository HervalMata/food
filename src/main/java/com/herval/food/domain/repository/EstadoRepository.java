package com.herval.food.domain.repository;

import com.herval.food.domain.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/*
 * Criado Por Herval Mata em 14/12/2019
 */
@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {
}

package com.herval.food.domain.repository;

import com.herval.food.domain.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
 * Criado Por Herval Mata em 17/12/2019
 */
@Repository
public interface UsuarioRepository extends CustomJpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}

package com.herval.food.domain.repository;

import com.herval.food.domain.model.Pedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * Criado Por Herval Mata em 17/12/2019
 */
@Repository
public interface PedidoRepository extends CustomJpaRepository<Pedido, Long> {

    @Query("from Pedido p join fetch p.cliente join fetch p.restaurante r join fetch r.cozinha")
    List<Pedido> findAll();
}

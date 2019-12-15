package com.herval.food.infrastructure.repository.spec;

import com.herval.food.domain.model.Restaurante;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

/*
 * Criado Por Herval Mata em 14/12/2019
 */
public class RestauranteSpecs {

    public static Specification<Restaurante> comFreteGratis() {
        return (root, query, builder) -> builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
    }
    public static Specification<Restaurante> comNomeSemelhante(String nome) {
        return (root, query, builder) -> builder.like(root.get("nome"), "%" + nome + "%");
    }
}

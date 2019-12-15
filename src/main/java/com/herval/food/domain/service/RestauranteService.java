package com.herval.food.domain.service;

import com.herval.food.domain.exception.EntidadeNaoEncontradaException;
import com.herval.food.domain.model.Cozinha;
import com.herval.food.domain.model.Restaurante;
import com.herval.food.domain.repository.CozinhaRepository;
import com.herval.food.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * Criado Por Herval Mata em 14/12/2019
 */
@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Restaurante salvar(Restaurante restaurante) {

        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.findById(cozinhaId).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de cozinha com código %d", cozinhaId)));

        restaurante.setCozinha(cozinha);

        return restauranteRepository.save(restaurante);
    }
}

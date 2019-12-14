package com.herval.food.jpa;

import com.herval.food.FoodApplication;
import com.herval.food.domain.model.Cozinha;
import com.herval.food.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

/*
 * Criado Por Herval Mata em 14/12/2019
 */
public class BuscaCozinhaMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(FoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);

        Cozinha cozinha = cozinhaRepository.buscar(1L);

        System.out.println(cozinha.getNome());

    }
}
package com.herval.food.jpa;

import com.herval.food.FoodApplication;
import com.herval.food.domain.model.Cidade;
import com.herval.food.domain.repository.CidadeRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

/*
 * Criado Por Herval Mata em 14/12/2019
 */
public class ConsultaCidadeMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(FoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CidadeRepository cidadeRepository = applicationContext.getBean(CidadeRepository.class);

        List<Cidade> cidades = cidadeRepository.listar();

        for (Cidade cidade : cidades) {
            System.out.println(cidade.getNome());
        }
    }
}

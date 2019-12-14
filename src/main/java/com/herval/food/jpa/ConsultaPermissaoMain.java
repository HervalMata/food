package com.herval.food.jpa;

import com.herval.food.FoodApplication;
import com.herval.food.domain.model.Permissao;
import com.herval.food.domain.repository.PermissaoRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

/*
 * Criado Por Herval Mata em 14/12/2019
 */
public class ConsultaPermissaoMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(FoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        PermissaoRepository permissaoRepository = applicationContext.getBean(PermissaoRepository.class);

        List<Permissao> permissoes = permissaoRepository.listar();

        for (Permissao permissao : permissoes) {
            System.out.printf("%s - %s\n", permissao.getNome(), permissao.getDescricao());
        }
    }
}

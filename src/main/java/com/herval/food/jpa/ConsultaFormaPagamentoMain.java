package com.herval.food.jpa;

import com.herval.food.FoodApplication;
import com.herval.food.domain.model.FormaPagamento;
import com.herval.food.domain.repository.FormaPagamentoRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

/*
 * Criado Por Herval Mata em 14/12/2019
 */
public class ConsultaFormaPagamentoMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(FoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        FormaPagamentoRepository formaPagamentoRepository = applicationContext.getBean(FormaPagamentoRepository.class);

        List<FormaPagamento> todasFormasPagamentos = formaPagamentoRepository.listar();

        for (FormaPagamento formaPagamento : todasFormasPagamentos) {
            System.out.println(formaPagamento.getDescricao());
        }
    }
}

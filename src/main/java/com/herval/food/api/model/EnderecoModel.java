package com.herval.food.api.model;

import lombok.Getter;
import lombok.Setter;

/*
 * Criado Por Herval Mata em 17/12/2019
 */
@Setter
@Getter
public class EnderecoModel {

    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private CidadeResumoModel cidade;
}

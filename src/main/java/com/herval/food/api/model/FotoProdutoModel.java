package com.herval.food.api.model;

import lombok.Getter;
import lombok.Setter;

/*
 * Criado Por Herval Mata em 18/12/2019
 */
@Setter
@Getter
public class FotoProdutoModel {

    private String nomeArquivo;
    private String descricao;
    private String contentType;
    private Long tamanho;
}

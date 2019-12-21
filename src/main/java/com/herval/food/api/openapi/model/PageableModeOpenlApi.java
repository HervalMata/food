package com.herval.food.api.openapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/*
 * Criado Por Herval Mata em 20/12/2019
 */
@ApiModel("Pageable")
@Getter
@Setter
public class PageableModeOpenlApi {

    @ApiModelProperty(example = "0", value = "Número da página (começa em 0)")
    private int page;

    @ApiModelProperty(example = "10", value = "Quantidade de elememtos por página")
    private int size;

    @ApiModelProperty(example = "nome,asc", value = "Nome da propriedade para ordenação")
    private List<String> sort;
}

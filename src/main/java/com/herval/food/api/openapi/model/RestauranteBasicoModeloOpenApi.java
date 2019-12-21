package com.herval.food.api.openapi.model;

import com.herval.food.api.model.CozinhaModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/*
 * Criado Por Herval Mata em 20/12/2019
 */
@ApiModel("RestauranteBasicoModel")
@Getter
@Setter
public class RestauranteBasicoModeloOpenApi {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Thaí Gourmet")
    private String nome;

    @ApiModelProperty(example = "12.00")
    private BigDecimal taxaFrete;

    private CozinhaModel cozinha;
}

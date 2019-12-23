package com.herval.food.api.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.herval.food.api.model.view.RestauranteView;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

/*
 * Criado Por Herval Mata em 16/12/2019
 */
@Relation(collectionRelation = "restaurantes")
@Setter
@Getter
public class RestauranteBasicoModel extends RepresentationModel<RestauranteBasicoModel> {

    @ApiModelProperty(example = "1")
    @JsonView({ RestauranteView.Resumo.class, RestauranteView.ApenasNome.class })
    private Long id;

    @ApiModelProperty(example = "Thai Gourmet")
    @JsonView({ RestauranteView.Resumo.class, RestauranteView.ApenasNome.class })
    private String nome;

    @ApiModelProperty(example = "12.00")
    @JsonView({ RestauranteView.Resumo.class })
    private BigDecimal taxaFrete;

    @JsonView({ RestauranteView.Resumo.class })
    private CozinhaModel cozinha;

    private Boolean ativo;
    private Boolean aberto;
    private EnderecoModel endereco;
}

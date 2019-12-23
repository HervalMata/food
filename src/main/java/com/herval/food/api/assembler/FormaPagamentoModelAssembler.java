package com.herval.food.api.assembler;

import com.herval.food.api.FoodLinks;
import com.herval.food.api.controller.FormaPagamentoController;
import com.herval.food.api.model.FormaPagamentoModel;
import com.herval.food.domain.model.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

/*
 * Criado Por Herval Mata em 17/12/2019
 */
@Component
public class FormaPagamentoModelAssembler extends RepresentationModelAssemblerSupport<FormaPagamento, FormaPagamentoModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FoodLinks foodLinks;

    public FormaPagamentoModelAssembler() {
        super(FormaPagamentoController.class, FormaPagamentoModel.class);
    }

    @Override
    public FormaPagamentoModel toModel(FormaPagamento formaPagamento) {
        FormaPagamentoModel formaPagamentoModel =
                createModelWithId(formaPagamento.getId(), formaPagamento);
        modelMapper.map(formaPagamento, FormaPagamentoModel.class);
        formaPagamentoModel.add(foodLinks.linkToFormasPagamento("formasPagamento"));
        return formaPagamentoModel;
    }

    @Override
    public CollectionModel<FormaPagamentoModel> toCollectionModel(Iterable<? extends FormaPagamento> entities) {
        return super.toCollectionModel(entities)
                .add(foodLinks.linkToFormasPagamento());
    }
}

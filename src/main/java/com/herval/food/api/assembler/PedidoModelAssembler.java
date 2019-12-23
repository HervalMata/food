package com.herval.food.api.assembler;

import com.herval.food.api.FoodLinks;
import com.herval.food.api.controller.*;
import com.herval.food.api.model.PedidoModel;
import com.herval.food.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/*
 * Criado Por Herval Mata em 17/12/2019
 */
@Component
public class PedidoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FoodLinks foodLinks;

    public PedidoModelAssembler() {
        super(PedidoController.class, PedidoModel.class);
    }

    @Override
    public PedidoModel toModel(Pedido pedido) {
        PedidoModel pedidoModel = createModelWithId(pedido.getId(), pedido);
        modelMapper.map(pedido, pedidoModel);
        pedidoModel.add(foodLinks.linkToPedidos());
        pedidoModel.getRestaurante().add(foodLinks.linkToRestaurante(pedido.getRestaurante().getId()));
        pedidoModel.getCliente().add(foodLinks.linkToUsuario(pedido.getCliente().getId()));
        pedidoModel.getFormaPagamento().add(foodLinks.linkToFormaPagamento(pedido.getFormaPagamento().getId()));
        pedidoModel.getEnderecoEntrega().getCidade().add(foodLinks.linkToCidade(pedido.getEnderecoEntrega().getCidade().getId()));
        pedidoModel.getItens().forEach(item -> {
            item.add(foodLinks.linkToProduto(pedidoModel.getRestaurante().getId(), item.getProdutoId(), "produto"));
        });

        return pedidoModel;
    }

    public List<PedidoModel> toCollectionModel(List<Pedido> pedidos) {
        return pedidos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}

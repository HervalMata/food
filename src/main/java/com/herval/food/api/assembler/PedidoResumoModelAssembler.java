package com.herval.food.api.assembler;

import com.herval.food.api.model.PedidoResumoModel;
import com.herval.food.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/*
 * Criado Por Herval Mata em 17/12/2019
 */
@Component
public class PedidoResumoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public PedidoResumoModel toModel(Pedido pedido) {
        return modelMapper.map(pedido, PedidoResumoModel.class);
    }

    public List<PedidoResumoModel> toCollectionModel(List<Pedido> pedidos) {
        return pedidos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}

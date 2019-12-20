package com.herval.food.domain.event;

import com.herval.food.domain.model.Pedido;
import lombok.AllArgsConstructor;
import lombok.Getter;

/*
 * Criado Por Herval Mata em 19/12/2019
 */
@Getter
@AllArgsConstructor
public class PedidoConfirmadoEvent {
    private Pedido pedido;
}

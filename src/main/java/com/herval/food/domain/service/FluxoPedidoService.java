package com.herval.food.domain.service;

import com.herval.food.domain.exception.NegocioException;
import com.herval.food.domain.model.Pedido;
import com.herval.food.domain.model.StatusPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

/*
 * Criado Por Herval Mata em 17/12/2019
 */
@Service
public class FluxoPedidoService {

    @Autowired
    private EmissaoPedidoService emissaoPedidoService;

    @Transactional
    public void confirmar(Long pedidoId) {
        Pedido pedido = emissaoPedidoService.buscarOuFalhar(pedidoId);
        pedido.confirmar();
    }

    @Transactional
    public void cancelar(Long pedidoId) {
        Pedido pedido = emissaoPedidoService.buscarOuFalhar(pedidoId);
        pedido.cancelar();
    }

    @Transactional
    public void entregar(Long pedidoId) {
        Pedido pedido = emissaoPedidoService.buscarOuFalhar(pedidoId);
        pedido.entregar();
    }
}

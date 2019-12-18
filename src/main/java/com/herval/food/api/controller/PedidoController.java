package com.herval.food.api.controller;

import com.herval.food.api.assembler.PedidoInputDisassembler;
import com.herval.food.api.assembler.PedidoModelAssembler;
import com.herval.food.api.assembler.PedidoResumoModelAssembler;
import com.herval.food.api.model.PedidoModel;
import com.herval.food.api.model.PedidoResumoModel;
import com.herval.food.api.model.input.PedidoInput;
import com.herval.food.domain.exception.EstadoNaoEncontradoException;
import com.herval.food.domain.exception.NegocioException;
import com.herval.food.domain.model.Pedido;
import com.herval.food.domain.model.Usuario;
import com.herval.food.domain.repository.PedidoRepository;
import com.herval.food.domain.service.EmissaoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/*
 * Criado Por Herval Mata em 17/12/2019
 */
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private EmissaoPedidoService emissaoPedidoService;

    @Autowired
    private PedidoModelAssembler pedidoModelAssembler;

    @Autowired
    private PedidoResumoModelAssembler pedidoResumoModelAssembler;

    @Autowired
    private PedidoInputDisassembler pedidoInputDisassembler;

    @GetMapping
    public List<PedidoResumoModel> listar() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return pedidoResumoModelAssembler.toCollectionModel(pedidos);
    }

    @GetMapping("/{pedidoId}")
    public PedidoModel buscar(@PathVariable Long pedidoId) {
        Pedido pedido = emissaoPedidoService.buscarOuFalhar(pedidoId);
        return pedidoModelAssembler.toModel(pedido);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoModel adicionar(@Valid @RequestBody PedidoInput pedidoInput) {
        try {
            Pedido pedido = pedidoInputDisassembler.toDomainObject(pedidoInput);
            pedido.setCliente(new Usuario());
            pedido.getCliente().setId(1L);

            pedido = emissaoPedidoService.emitir(pedido);

            return pedidoModelAssembler.toModel(pedido);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }
}

package com.herval.food.api.controller;

import com.herval.food.api.assembler.PedidoInputDisassembler;
import com.herval.food.api.assembler.PedidoModelAssembler;
import com.herval.food.api.assembler.PedidoResumoModelAssembler;
import com.herval.food.api.model.PedidoModel;
import com.herval.food.api.model.PedidoResumoModel;
import com.herval.food.api.model.input.PedidoInput;
import com.herval.food.api.openapi.controller.PedidoControllerOpenApi;
import com.herval.food.core.data.PageWrapper;
import com.herval.food.core.data.PageableTranslator;
import com.herval.food.domain.exception.EstadoNaoEncontradoException;
import com.herval.food.domain.exception.NegocioException;
import com.herval.food.domain.model.Pedido;
import com.herval.food.domain.model.Usuario;
import com.herval.food.domain.repository.PedidoRepository;
import com.herval.food.domain.filter.PedidoFilter;
import com.herval.food.domain.service.EmissaoPedidoService;
import com.herval.food.infrastructure.repository.spec.PedidoSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/*
 * Criado Por Herval Mata em 17/12/2019
 */
@RestController
@RequestMapping(path = "/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoController implements PedidoControllerOpenApi {

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

    @Autowired
    private PagedResourcesAssembler<Pedido> pedidoPagedResourcesAssembler;

    @Override
    @GetMapping
    public PagedModel<PedidoResumoModel> pesquisar(PedidoFilter filtro, @PageableDefault(size = 10)Pageable pageable) {
        Pageable pageableTraduzido = traduzirPageable(pageable);
        Page<Pedido> pedidoPage = pedidoRepository.findAll(PedidoSpecs.usandoFiltro(filtro), pageableTraduzido);

        pedidoPage = new PageWrapper<>(pedidoPage, pageable);
        return pedidoPagedResourcesAssembler
                .toModel(pedidoPage, pedidoResumoModelAssembler);
    }

    @GetMapping("/{codigoPedido}")
    public PedidoModel buscar(@PathVariable String codigoPedido) {
        Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);
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

    private Pageable traduzirPageable(Pageable apiPageable) {
        var mapeamento = Map.of(
                "codigo", "codigo",
                "subtotal", "subtotal",
                "taxaFrete", "taxaFrete",
                "valorTotal", "valorTotal",
                "dataCriacao", "dataCriacao",
                "restaurante.nome", "restaurante.nome",
                "restaurante.id", "restaurante.id",
                "cliente.id", "cliente.id",
                "nomeCliente", "cliente.nome"
        );
        return PageableTranslator.translate(apiPageable, mapeamento);
    }
}

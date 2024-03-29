package com.herval.food.api;

import com.herval.food.api.controller.*;
import org.springframework.hateoas.*;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/*
 * Criado Por Herval Mata em 22/12/2019
 */
@Component
public class FoodLinks {

    public static final TemplateVariables PAGINACAO_VARIABLES = new TemplateVariables(
            new TemplateVariable("page", TemplateVariable.VariableType.REQUEST_PARAM),
            new TemplateVariable("size", TemplateVariable.VariableType.REQUEST_PARAM),
            new TemplateVariable("sort", TemplateVariable.VariableType.REQUEST_PARAM)
    );

    public Link linkToPedidos() {
        TemplateVariables filtroVariables = new TemplateVariables(
                new TemplateVariable("clienteId", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("restauranteId", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("dataCriacaoInicio", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("dataCriacaoFinal", TemplateVariable.VariableType.REQUEST_PARAM)
        );
        String pedidosUrl = linkTo(PedidoController.class).toUri().toString();
        return new Link(UriTemplate.of(pedidosUrl,
                PAGINACAO_VARIABLES.concat(filtroVariables)), "pedidos");
    }

    public Link linkToConfirmacaoPedido(String codigoPedido, String rel) {
        return linkTo(methodOn(FluxoPedidoController.class)
                .confirmar(codigoPedido)).withRel(rel);
    }

    public Link linkToEntregaPedido(String codigoPedido, String rel) {
        return linkTo(methodOn(FluxoPedidoController.class)
                .entregar(codigoPedido)).withRel(rel);
    }

    public Link linkToCancelamentoPedido(String codigoPedido, String rel) {
        return linkTo(methodOn(FluxoPedidoController.class)
                .cancelar(codigoPedido)).withRel(rel);
    }

    public Link linkToRestaurante(Long restaunteId, String rel) {
        return linkTo(methodOn(RestauranteController.class)
                      .buscar(restaunteId)).withRel(rel);
    }

    public Link linkToRestaurante(Long restaunteId) {
        return linkToRestaurante(restaunteId, IanaLinkRelations.SELF.value());
    }

    public Link linkToRestaurantes(String rel) {

        return linkTo(RestauranteController.class).withRel(rel);
    }

    public Link linkToRestaurantes() {
        return linkToRestaurantes(IanaLinkRelations.SELF.value());
    }

    public Link linkToRestauranteFormasPagamento(Long restaunteId, String rel) {
        return linkTo(methodOn(RestauranteFormaPagamentoController.class)
                .listar(restaunteId)).withRel(rel);
    }

    public Link linkToRestauranteFormasPagamento(Long restaunteId) {
        return linkToRestauranteFormasPagamento(restaunteId, IanaLinkRelations.SELF.value());
    }

    public Link linkToRestauranteFormasPagamentoDesassociacao(Long restaunteId, Long formaPagamentoId, String rel) {
        return linkTo(methodOn(RestauranteFormaPagamentoController.class)
                .desassociar(restaunteId, formaPagamentoId)).withRel(rel);
    }

    public Link linkToRestauranteFormasPagamentoAssociacao(Long restaunteId, String rel) {
        return linkTo(methodOn(RestauranteFormaPagamentoController.class)
                .associar(restaunteId, null)).withRel(rel);
    }

    public Link linkToRestauranteAbertura(Long restaunteId, String rel) {
        return linkTo(methodOn(RestauranteController.class)
                .abrir(restaunteId)).withRel(rel);
    }

    public Link linkToRestauranteFechamento(Long restaunteId, String rel) {
        return linkTo(methodOn(RestauranteController.class)
                .fechar(restaunteId)).withRel(rel);
    }

    public Link linkToRestauranteInativacao(Long restaunteId, String rel) {
        return linkTo(methodOn(RestauranteController.class)
                .inativar(restaunteId)).withRel(rel);
    }

    public Link linkToRestauranteAtivacao(Long restaunteId, String rel) {
        return linkTo(methodOn(RestauranteController.class)
                .ativar(restaunteId)).withRel(rel);
    }

    public Link linkToUsuario(Long usuarioId, String rel) {
        return linkTo(methodOn(UsuarioController.class)
                .buscar(usuarioId)).withRel(rel);
    }

    public Link linkToUsuario(Long usuarioId) {
        return linkToUsuario(usuarioId, IanaLinkRelations.SELF.value());
    }

    public Link linkToUsuarios(String rel) {
        return linkTo(UsuarioController.class).withRel(rel);
    }

    public Link linkToUsuarios() {
        return linkToUsuarios(IanaLinkRelations.SELF.value());
    }

    public Link linkToGruposUsuario(Long usuarioId, String rel) {
        return linkTo(methodOn(UsuarioGrupoController.class)
                .listar(usuarioId)).withRel(rel);
    }

    public Link linkToGruposUsuario(Long usuarioId) {
        return linkToGruposUsuario(usuarioId, IanaLinkRelations.SELF.value());
    }

    public Link linkToResponsaveisRestaurante(Long restauranteId, String rel) {
        return linkTo(methodOn(RestauranteUsuarioResponsavelController.class)
                .listar(restauranteId)).withRel(rel);
    }

    public Link linkToResponsaveisRestaurante(Long restauranteId) {
        return linkToResponsaveisRestaurante(restauranteId, IanaLinkRelations.SELF.value());
    }

    public Link linkToResponsaveisRestauranteAssociacao(Long restaunteId, String rel) {
        return linkTo(methodOn(RestauranteUsuarioResponsavelController.class)
                .associar(restaunteId, null)).withRel(rel);
    }

    public Link linkToResponsaveisRestauranteDesassociacao(Long restaunteId, Long usuarioId, String rel) {
        return linkTo(methodOn(RestauranteUsuarioResponsavelController.class)
                .desassociar(restaunteId, usuarioId)).withRel(rel);
    }

    public Link linkToFormaPagamento(Long formaPagamentoId, String rel) {
        return linkTo(methodOn(FormaPagamentoController.class)
                .buscar(formaPagamentoId, null)).withRel(rel);
    }

    public Link linkToFormaPagamento(Long formaPagamentoId) {
        return linkToFormaPagamento(formaPagamentoId, IanaLinkRelations.SELF.value());
    }

    public Link linkToFormasPagamento(String rel) {
        return linkTo(FormaPagamentoController.class).withRel(rel);
    }

    public Link linkToFormasPagamento() {
        return linkToFormasPagamento(IanaLinkRelations.SELF.value());
    }

    public Link linkToCidade(Long cidadeId, String rel) {
        return linkTo(methodOn(CidadeController.class)
                .buscar(cidadeId)).withRel(rel);
    }

    public Link linkToCidade(Long cidadeId) {
        return linkToCidade(cidadeId, IanaLinkRelations.SELF.value());
    }

    public Link linkToCidades(String rel) {
        return linkTo(CidadeController.class).withRel(rel);
    }

    public Link linkToCidades() {
        return linkToCidades(IanaLinkRelations.SELF.value());
    }

    public Link linkToEstado(Long estadoId, String rel) {
        return linkTo(methodOn(EstadoController.class)
                .buscar(estadoId)).withRel(rel);
    }

    public Link linkToEstado(Long estadoId) {
        return linkToEstado(estadoId, IanaLinkRelations.SELF.value());
    }

    public Link linkToEstados(String rel) {
        return linkTo(EstadoController.class).withRel(rel);
    }

    public Link linkToEstados() {
        return linkToEstados(IanaLinkRelations.SELF.value());
    }

    public Link linkToProduto(Long restauranteId, Long produtoId, String rel) {
        return linkTo(methodOn(RestauranteProdutoController.class)
                .buscar(restauranteId, produtoId)).withRel(rel);
    }

    public Link linkToProduto(Long restauranteId, Long produtoId) {
        return linkToProduto(restauranteId, produtoId, IanaLinkRelations.SELF.value());
    }

    public Link linkToCozinha(Long cozinhaId, String rel) {
        return linkTo(methodOn(CozinhaController.class)
                .buscar(cozinhaId)).withRel(rel);
    }

    public Link linkToCozinha(Long cozinhaId) {
        return linkToCozinha(cozinhaId, IanaLinkRelations.SELF.value());
    }

    public Link linkToCozinhas(String rel) {
        return linkTo(CozinhaController.class).withRel(rel);
    }

    public Link linkToCozinhas() {
        return linkToCozinhas(IanaLinkRelations.SELF.value());
    }
}

package com.herval.food.core.modelmapper;

import com.herval.food.api.model.EnderecoModel;
import com.herval.food.api.model.input.ItemPedidoInput;
import com.herval.food.domain.model.Endereco;
import com.herval.food.domain.model.ItemPedido;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * Criado Por Herval Mata em 16/12/2019
 */
@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();
        //modelMapper.createTypeMap(Restaurante.class, RestauranteModel.class)
         //       .addMapping(Restaurante::getTaxaFrete, RestauranteModel::setTaxaFrete);
        modelMapper.createTypeMap(ItemPedidoInput.class, ItemPedido.class)
                .addMappings(mapper -> mapper.skip(ItemPedido::setId));

        var endercoToEnderecoModelTypeMap = modelMapper.createTypeMap(
                Endereco.class, EnderecoModel.class
        );
        endercoToEnderecoModelTypeMap.<String>addMapping(
                enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(),
                (enderecoModelDest, value) -> enderecoModelDest.getCidade().setEstado(value));
        return new ModelMapper();
    }
}

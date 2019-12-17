package com.herval.food.api.assembler;

import com.herval.food.api.model.PermissaoModel;
import com.herval.food.domain.model.Permissao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/*
 * Criado Por Herval Mata em 17/12/2019
 */
@Component
public class PermissaoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public PermissaoModel toModel(Permissao permissao) {
        return modelMapper.map(permissao, PermissaoModel.class);
    }

    public List<PermissaoModel> toCollectionModel(List<Permissao> permissoes) {
        return permissoes.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}

package com.herval.food.api.assembler;

import com.herval.food.api.model.input.CidadeInput;
import com.herval.food.domain.model.Cidade;
import com.herval.food.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
 * Criado Por Herval Mata em 16/12/2019
 */
@Component
public class CidadeInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Cidade toDomainObject(CidadeInput cidadeInput) {
        return modelMapper.map(cidadeInput, Cidade.class);
    }

    public void copyToDomainObject(CidadeInput cidadeInput, Cidade cidade) {
        cidade.setEstado(new Estado());
        modelMapper.map(cidadeInput, cidade);
    }
}

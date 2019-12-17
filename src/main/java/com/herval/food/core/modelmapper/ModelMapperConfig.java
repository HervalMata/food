package com.herval.food.core.modelmapper;

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

        return new ModelMapper();
    }
}

package com.herval.food.core.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.BindingResult;

/*
 * Criado Por Herval Mata em 15/12/2019
 */
@AllArgsConstructor
@Getter
public class ValidacaoException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private BindingResult bindingResult;
}

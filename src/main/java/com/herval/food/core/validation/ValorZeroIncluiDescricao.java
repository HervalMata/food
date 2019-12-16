package com.herval.food.core.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * Criado Por Herval Mata em 15/12/2019
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { ValorZeroIncluiDescricaoValidator.class })
public @interface ValorZeroIncluiDescricao {

    String message() default "descrição obrigatória inválida";
    Class<?>[] groups() default {};
    Class<? extends Payload> [] payload() default {};
    String valorField();
    String descricaoField();
    String descricaoObrigatoria();
}

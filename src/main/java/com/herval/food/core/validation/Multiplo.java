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
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { MultiploValidator.class })
public @interface Multiplo {

    String message() default "múltiplo inválido";
    Class<?>[] groups() default {};
    Class<? extends Payload> [] payload() default {};
    int numero();
}

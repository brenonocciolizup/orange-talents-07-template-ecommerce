package br.com.zupacademy.brenonoccioli.mercadolivre.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {UniqueValueValidator.class})
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueValue {

    String message() default "{o campo já foi cadastrado e não pode ser duplicado}";

    Class<?>[] groups() default{ };

    Class<? extends Payload>[] payload() default{};

    String field();
    Class<?> domainClass();
}

package br.com.zupacademy.brenonoccioli.mercadolivre.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Constraint(validatedBy = QuantidadeValidValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface QuantidadeValid {

    String message() default "{quantidade solicitada maior que estoque do produto}";

    Class<?>[] groups() default{ };

    Class<? extends Payload>[] payload() default{};

}

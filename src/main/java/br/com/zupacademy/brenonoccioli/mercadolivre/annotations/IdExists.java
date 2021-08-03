package br.com.zupacademy.brenonoccioli.mercadolivre.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = IdExistsValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IdExists {

    String message() default "id informado não existe no banco dados, informe um id válido";

    Class<?>[] groups() default{};

    Class<? extends Payload>[] payload() default{};

    String field();
    Class<?> domainClass();
}

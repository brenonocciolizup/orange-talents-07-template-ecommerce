package br.com.zupacademy.brenonoccioli.mercadolivre.annotations;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {
    private String domainField;
    private Class<?> clazz;
    @PersistenceContext
    private EntityManager em;

    @Override
    public void initialize(UniqueValue object) {
        domainField = object.field();
        clazz = object.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Query query = em.createQuery("select 1 from "+clazz.getName()+
                " where "+domainField+" =:value");
        query.setParameter("value", value);
        List<?> list = query.getResultList();
        Assert.state(list.size()<=1, "Foi encontrado mais de um "
                +clazz+" com o atributo "+domainField+" = "+value);

        return list.isEmpty();
    }
}

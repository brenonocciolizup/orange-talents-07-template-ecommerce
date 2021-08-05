package br.com.zupacademy.brenonoccioli.mercadolivre.config.validacao;

import br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto.form.ProdutoForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Set;

@Component
public class CaracteristicasNomeUnicoValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return ProdutoForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        if(errors.hasErrors()){
            return;
        }

        ProdutoForm form = (ProdutoForm) object;
        Set<String> nomesIguais = form.buscaCaracteristicasIguais();
        if(!nomesIguais.isEmpty()){
            errors.rejectValue("caracteristicas", null, "não é possível adicionar características iguais" );
        }

    }

}

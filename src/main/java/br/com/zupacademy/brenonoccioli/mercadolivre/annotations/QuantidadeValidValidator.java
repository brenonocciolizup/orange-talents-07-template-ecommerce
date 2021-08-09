package br.com.zupacademy.brenonoccioli.mercadolivre.annotations;

import br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto.Produto;
import br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto.ProdutoRepository;
import br.com.zupacademy.brenonoccioli.mercadolivre.finalizarcompra.form.CompraForm;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;


public class QuantidadeValidValidator implements ConstraintValidator<QuantidadeValid, CompraForm> {
    @Autowired
    private ProdutoRepository repository;

    @Override
    public boolean isValid(CompraForm form, ConstraintValidatorContext context) {
        Optional<Produto> produtoOptional = repository.findById(form.getIdProduto());
        if(produtoOptional.isEmpty()){
            return false;
        }

        Produto produto = produtoOptional.get();
        int estoque = produto.getQuantidade();
        boolean estoqueValido = form.getQuantidade() <= estoque;
        return estoqueValido;
    }

}

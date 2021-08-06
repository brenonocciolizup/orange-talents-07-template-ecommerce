package br.com.zupacademy.brenonoccioli.mercadolivre.adicionapergunta.form;

import br.com.zupacademy.brenonoccioli.mercadolivre.adicionapergunta.PerguntaSobreProduto;
import br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto.Produto;
import br.com.zupacademy.brenonoccioli.mercadolivre.usuario.Usuario;

import javax.validation.constraints.NotBlank;

public class PerguntaSobreProdutoForm {

    @NotBlank
    private String titulo;


    public PerguntaSobreProduto toModel(Usuario dono, Produto produto){
        return new PerguntaSobreProduto(titulo, dono, produto);
    }

    public String getTitulo() {
        return titulo;
    }

}

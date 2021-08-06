package br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto.form;

import br.com.zupacademy.brenonoccioli.mercadolivre.annotations.IdExists;
import br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto.OpiniaoSobreProduto;
import br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto.Produto;
import br.com.zupacademy.brenonoccioli.mercadolivre.usuario.Usuario;

import javax.persistence.Entity;
import javax.validation.constraints.*;


public class OpiniaoSobreProdutoForm {

    @NotNull
    @Min(1)
    @Max(5)
    private Integer nota;

    @NotBlank
    private String titulo;

    @NotBlank
    @Size(max=500)
    private String descricao;


    public OpiniaoSobreProdutoForm(Integer nota, String titulo, String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public OpiniaoSobreProduto toModel(Usuario dono, Produto produto){
        return new OpiniaoSobreProduto(nota, titulo, descricao, dono, produto);
    }

}

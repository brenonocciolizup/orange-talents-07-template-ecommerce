package br.com.zupacademy.brenonoccioli.mercadolivre.categoria;

import io.jsonwebtoken.lang.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Column(unique = true)
    private String nome;

    @ManyToOne
    private Categoria categoriaMae;

    @Deprecated
    public Categoria(){}

    public Categoria(String nome){
        this.nome = nome;
    }

    //Optei pela sobrecarga de construtores para evitar passar um dado null,
    //já que categoriaMae não é obrigatória
    public Categoria(String nome, Categoria categoria){
        Assert.notNull(categoria, "categoria não pode ser nula");
        this.nome = nome;
        this.categoriaMae = categoria;
    }

    public String getNome() {
        return nome;
    }

    public Categoria getCategoriaMae() {
        return categoriaMae;
    }

    //Como o atributo não é obrigatório, conseguimos alterar depois com o set
    public void setCategoriaMae(Categoria categoriaMae) {
        this.categoriaMae = categoriaMae;
    }
}

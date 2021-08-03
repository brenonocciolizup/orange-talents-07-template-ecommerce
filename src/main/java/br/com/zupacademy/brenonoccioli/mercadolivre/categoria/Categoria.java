package br.com.zupacademy.brenonoccioli.mercadolivre.categoria;

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

    public Categoria(String nome, Categoria categoria){
        this.nome = nome;
        this.categoriaMae = categoria;
    }

    public String getNome() {
        return nome;
    }
}

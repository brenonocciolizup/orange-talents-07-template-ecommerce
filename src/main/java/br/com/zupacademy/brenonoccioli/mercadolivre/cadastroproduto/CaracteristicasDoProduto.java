package br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class CaracteristicasDoProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    @NotNull
    @ManyToOne
    private Produto produto;

    @Deprecated
    public CaracteristicasDoProduto(){}

    public CaracteristicasDoProduto(String nome, String descricao, Produto produto){
        this.nome = nome;
        this.descricao = descricao;
        this.produto = produto;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }


}

package br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto;

import br.com.zupacademy.brenonoccioli.mercadolivre.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class OpiniaoSobreProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(1)
    @Max(5)
    private Integer nota;

    @NotBlank
    private String titulo;

    @NotBlank
    @Size(max=500)
    private String descricao;

    @NotNull
    @ManyToOne
    private Usuario dono;

    @NotNull
    @ManyToOne
    private Produto produto;

    @Deprecated
    public OpiniaoSobreProduto(){}

    public OpiniaoSobreProduto(Integer nota, String titulo, String descricao, Usuario dono, Produto produto) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dono = dono;
        this.produto = produto;
    }

    public Integer getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getDonoOpiniao() {
        return dono.getEmail();
    }
}

package br.com.zupacademy.brenonoccioli.mercadolivre.adicionapergunta;

import br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto.Produto;
import br.com.zupacademy.brenonoccioli.mercadolivre.usuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class PerguntaSobreProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    private LocalDateTime instanteCriacao = LocalDateTime.now();

    @NotNull
    @ManyToOne
    private Usuario dono;

    @NotNull
    @ManyToOne
    private Produto produto;

    @Deprecated
    public PerguntaSobreProduto() {
    }

    public PerguntaSobreProduto(String titulo, Usuario dono, Produto produto) {
        this.titulo = titulo;
        this.dono = dono;
        this.produto = produto;
    }

    public String getTitulo() {
        return titulo;
    }

    public Produto getProduto() {
        return produto;
    }

    public LocalDateTime getInstanteCriacao() {
        return instanteCriacao;
    }
}

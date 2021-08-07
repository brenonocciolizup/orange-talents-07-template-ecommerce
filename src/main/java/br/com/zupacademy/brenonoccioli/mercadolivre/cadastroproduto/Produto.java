package br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto;

import br.com.zupacademy.brenonoccioli.mercadolivre.adicionapergunta.PerguntaSobreProduto;
import br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto.form.CaracteristicasDoProdutoForm;
import br.com.zupacademy.brenonoccioli.mercadolivre.categoria.Categoria;
import br.com.zupacademy.brenonoccioli.mercadolivre.usuario.Usuario;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;

import java.util.List;
import java.util.Set;


@Entity
public class Produto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotNull @Positive
    private BigDecimal preco;

    @NotNull
    @PositiveOrZero
    private Integer quantidade;

    @NotBlank
    @Size(max=1000)
    private String descricao;

    private LocalDateTime instanteCadastro = LocalDateTime.now();

    @Size(min=3)
    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<CaracteristicasDoProduto> caracteristicas = new HashSet<>();

    @NotNull
    @ManyToOne
    private Categoria categoria;

    @NotNull
    @ManyToOne
    private Usuario dono;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<ImagemProduto> imagens = new HashSet<>();

    @OneToMany(mappedBy = "produto")
    private List<OpiniaoSobreProduto> opinioes = new ArrayList<>();

    @OneToMany(mappedBy = "produto")
    private List<PerguntaSobreProduto> perguntas = new ArrayList<>();

    @Deprecated
    public Produto() {
    }

    public Produto(String nome, BigDecimal preco, Integer quantidade,
                   String descricao, Categoria categoria, Usuario dono,
                   Set<CaracteristicasDoProdutoForm> caracteristicasForm){
        this.nome=nome;
        this.preco=preco;
        this.quantidade=quantidade;
        this.descricao=descricao;
        this.categoria=categoria;
        this.dono = dono;
        caracteristicasForm.forEach(c -> this.caracteristicas.add(c.toModel(this)));
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getInstanteCadastro() {
        return instanteCadastro;
    }

    public Set<CaracteristicasDoProduto> getCaracteristicas() {
        return caracteristicas;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Usuario getDono() {
        return dono;
    }

    public Set<ImagemProduto> getImagens() {
        return imagens;
    }

    public List<OpiniaoSobreProduto> getOpinioes() {
        return opinioes;
    }

    public List<PerguntaSobreProduto> getPerguntas() {
        return perguntas;
    }

    public void insereImagem(Set<String> links) {
        links.forEach(link -> {
            ImagemProduto img = new ImagemProduto(this, link);
            this.imagens.add(img);
        });
    }


    public void adicionaOpiniao(OpiniaoSobreProduto opiniao) {
        this.opinioes.add(opiniao);
    }
}

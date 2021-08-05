package br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto;

import br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto.form.CaracteristicasDoProdutoForm;
import br.com.zupacademy.brenonoccioli.mercadolivre.categoria.Categoria;
import br.com.zupacademy.brenonoccioli.mercadolivre.usuario.Usuario;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
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
}
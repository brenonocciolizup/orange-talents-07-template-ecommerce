package br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto.dto;

import br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto.CaracteristicasDoProduto;
import br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto.Produto;
import br.com.zupacademy.brenonoccioli.mercadolivre.categoria.Categoria;
import br.com.zupacademy.brenonoccioli.mercadolivre.usuario.Usuario;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProdutoDto {

    private String nome;
    private BigDecimal preco;
    private Integer qtdDisponivel;
    private String descricao;
    private Usuario dono;
    private Categoria categoria;
    private Set<CaracteristicasDoProduto> caracteristicas = new HashSet<>();
    private LocalDateTime instanteCadastro;


    public ProdutoDto(Produto produto) {
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
        this.qtdDisponivel = produto.getQuantidade();
        this.descricao = produto.getDescricao();
        this.dono = produto.getDono();
        this.categoria = produto.getCategoria();
        this.instanteCadastro = produto.getInstanteCadastro();
        this.caracteristicas.addAll(produto.getCaracteristicas());
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Integer getQtdDisponivel() {
        return qtdDisponivel;
    }

    public String getDescricao() {
        return descricao;
    }

    public Usuario getDono() {
        return dono;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Set<CaracteristicasDoProduto> getCaracteristicas() {
        return caracteristicas;
    }

    public LocalDateTime getInstanteCadastro() {
        return instanteCadastro;
    }
}

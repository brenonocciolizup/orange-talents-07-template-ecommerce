package br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto.dto;

import br.com.zupacademy.brenonoccioli.mercadolivre.adicionapergunta.PerguntaSobreProduto;
import br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto.CaracteristicasDoProduto;
import br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto.ImagemProduto;
import br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto.OpiniaoSobreProduto;
import br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto.Produto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DetalheProdutoDto {

    private Set<ImagemProduto> links;
    private String nome;
    private BigDecimal preco;
    private Set<CaracteristicasDoProduto> caracteristicas = new HashSet<>();
    private String descricao;
    private Double mediaNotas;
    private Integer quantidadeNotas;
    private List<OpiniaoSobreProduto> opinioes;
    private List<PerguntaSobreProduto> perguntas;

    public DetalheProdutoDto(Produto produto, double mediaNotas) {
        this.links = produto.getImagens();
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
        this.caracteristicas = produto.getCaracteristicas();
        this.descricao = produto.getDescricao();
        this.mediaNotas = mediaNotas;
        this.quantidadeNotas = produto.getOpinioes().size();
        this.opinioes = produto.getOpinioes();
        this.perguntas = produto.getPerguntas();
    }

    public Set<ImagemProduto> getLinks() {
        return links;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Set<CaracteristicasDoProduto> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getMediaNotas() {
        return mediaNotas;
    }

    public Integer getQuantidadeNotas() {
        return quantidadeNotas;
    }

    public List<OpiniaoSobreProduto> getOpinioes() {
        return opinioes;
    }

    public List<PerguntaSobreProduto> getPerguntas() {
        return perguntas;
    }
}

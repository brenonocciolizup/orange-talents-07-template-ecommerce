package br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto.dto;

import br.com.zupacademy.brenonoccioli.mercadolivre.adicionapergunta.PerguntaSobreProduto;
import br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto.CaracteristicasDoProduto;
import br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto.ImagemProduto;
import br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto.OpiniaoSobreProduto;
import br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto.Produto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DetalheProdutoDto {

    private Set<ImagemProduto> links;
    private String nome;
    private BigDecimal preco;
    private Set<DetalheCaracteristicaDto> caracteristicas = new HashSet<>();
    private String descricao;
    private Double mediaNotas;
    private Integer quantidadeNotas;
    private List<DetalheOpiniaoDto> opinioes;
    private List<DetalhePerguntasDto> perguntas;

    public DetalheProdutoDto(Produto produto, double mediaNotas) {
        this.links = produto.getImagens();
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
        this.caracteristicas = produto.getCaracteristicas().stream()
                .map(DetalheCaracteristicaDto::new).collect(Collectors.toSet());
        this.descricao = produto.getDescricao();
        this.mediaNotas = mediaNotas;
        this.quantidadeNotas = produto.getOpinioes().size();
        this.opinioes = produto.getOpinioes().stream().map(DetalheOpiniaoDto::new).collect(Collectors.toList());
        this.perguntas = produto.getPerguntas().stream().map(DetalhePerguntasDto::new).collect(Collectors.toList());
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

    public Set<DetalheCaracteristicaDto> getCaracteristicas() {
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

    public List<DetalheOpiniaoDto> getOpinioes() {
        return opinioes;
    }

    public List<DetalhePerguntasDto> getPerguntas() {
        return perguntas;
    }
}

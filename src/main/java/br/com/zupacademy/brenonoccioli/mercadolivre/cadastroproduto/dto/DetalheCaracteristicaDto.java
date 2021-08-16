package br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto.dto;

import br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto.CaracteristicasDoProduto;
import br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto.Produto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DetalheCaracteristicaDto {

    private String nome;

    private String descricao;

    public DetalheCaracteristicaDto(CaracteristicasDoProduto caracteristica) {
            this.nome = caracteristica.getNome();
            this.descricao = caracteristica.getDescricao();
    }

    public String getDescricao() {
        return descricao;
    }

    public String getNome() {
        return nome;
    }
}

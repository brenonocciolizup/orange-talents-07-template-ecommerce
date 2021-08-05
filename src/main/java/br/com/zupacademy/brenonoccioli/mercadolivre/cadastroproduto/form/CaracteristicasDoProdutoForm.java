package br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto.form;

import br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto.CaracteristicasDoProduto;
import br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto.Produto;

import javax.validation.constraints.NotBlank;
import java.util.Objects;


public class CaracteristicasDoProdutoForm {
    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    public CaracteristicasDoProdutoForm(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public CaracteristicasDoProduto toModel(Produto produto) {
        return new CaracteristicasDoProduto(nome, descricao, produto);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaracteristicasDoProdutoForm that = (CaracteristicasDoProdutoForm) o;
        return nome.equals(that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

}

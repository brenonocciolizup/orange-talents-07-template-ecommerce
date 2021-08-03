package br.com.zupacademy.brenonoccioli.mercadolivre.categoria.dto;

public class CategoriaDto {


    private String nome;

    public CategoriaDto(String nome) {
        this.nome = nome;
    }

    //Uso do get para o framework conseguir pegar os dados de resposta
    public String getNome() {
        return nome;
    }
}

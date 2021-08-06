package br.com.zupacademy.brenonoccioli.mercadolivre.adicionapergunta.dto;

import br.com.zupacademy.brenonoccioli.mercadolivre.adicionapergunta.PerguntaSobreProduto;

import java.time.LocalDateTime;

public class PerguntaSobreProdutoDto {

    private String titulo;


    private LocalDateTime instanteCriacao;


    public PerguntaSobreProdutoDto(PerguntaSobreProduto pergunta){
        this.titulo = pergunta.getTitulo();
        this.instanteCriacao = pergunta.getInstanteCriacao();

    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDateTime getInstanteCriacao() {
        return instanteCriacao;
    }


}

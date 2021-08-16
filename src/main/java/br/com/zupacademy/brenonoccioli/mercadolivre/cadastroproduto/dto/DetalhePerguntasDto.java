package br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto.dto;

import br.com.zupacademy.brenonoccioli.mercadolivre.adicionapergunta.PerguntaSobreProduto;

import java.time.LocalDateTime;

public class DetalhePerguntasDto {

    private String titulo;

    private LocalDateTime instanteCriacao;

    private String donoPergunta;

    public DetalhePerguntasDto(PerguntaSobreProduto pergunta){
        this.titulo = pergunta.getTitulo();
        this.instanteCriacao = pergunta.getInstanteCriacao();
        this.donoPergunta = pergunta.getDono().getEmail();
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDateTime getInstanteCriacao() {
        return instanteCriacao;
    }

    public String getDonoPergunta() {
        return donoPergunta;
    }
}

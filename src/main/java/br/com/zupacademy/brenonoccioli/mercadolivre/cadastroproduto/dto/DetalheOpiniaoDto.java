package br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto.dto;

import br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto.OpiniaoSobreProduto;
import br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto.Produto;

public class DetalheOpiniaoDto {

    private Integer nota;

    private String titulo;

    private String descricao;

    private String donoOpiniao;

    public DetalheOpiniaoDto(OpiniaoSobreProduto opiniao) {
        this.nota = opiniao.getNota();
        this.titulo = opiniao.getTitulo();
        this.descricao = opiniao.getDescricao();
        this.donoOpiniao = opiniao.getDono().getEmail();
    }

    public Integer getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getDonoOpiniao() {
        return donoOpiniao;
    }
}

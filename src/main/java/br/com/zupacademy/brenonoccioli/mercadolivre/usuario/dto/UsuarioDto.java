package br.com.zupacademy.brenonoccioli.mercadolivre.usuario.dto;

import java.time.LocalDateTime;

public class UsuarioDto {

    private String email;
    private LocalDateTime dataCriacao;

    public UsuarioDto(String email, LocalDateTime dataCriacao){
        this.email = email;
        this.dataCriacao = dataCriacao;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

}


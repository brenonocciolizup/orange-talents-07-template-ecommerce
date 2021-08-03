package br.com.zupacademy.brenonoccioli.mercadolivre.usuario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;


public class SenhaEncodada {
    private String senha;

    @Deprecated
    public SenhaEncodada(){}

    public SenhaEncodada(String senha){
        Assert.isTrue(senha.length() >= 6, "senha deve ter no mínimo 6 caracteres");
        this.senha = senha;
    }

    public String encode(){
        return new BCryptPasswordEncoder().encode(senha);
    }

}

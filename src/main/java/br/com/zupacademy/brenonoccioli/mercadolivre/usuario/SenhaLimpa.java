package br.com.zupacademy.brenonoccioli.mercadolivre.usuario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

//Representa senha limpa que será encodada na entidade usuario
public class SenhaLimpa {
    private String senhaLimpa;

    @Deprecated
    public SenhaLimpa(){}

    public SenhaLimpa(String senha){
        Assert.isTrue(senha.length() >= 6, "senha deve ter no mínimo 6 caracteres");
        this.senhaLimpa = senha;
    }

    public String encodar(){
        String senhaEncodada = new BCryptPasswordEncoder().encode(this.senhaLimpa);
        return senhaEncodada;
    }

}

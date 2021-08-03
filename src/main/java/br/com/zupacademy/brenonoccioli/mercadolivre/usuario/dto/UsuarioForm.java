package br.com.zupacademy.brenonoccioli.mercadolivre.usuario.dto;

import br.com.zupacademy.brenonoccioli.mercadolivre.anotacoes.UniqueValue;
import br.com.zupacademy.brenonoccioli.mercadolivre.usuario.Usuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioForm {

    @Column(unique = true)
    @Email
    @NotBlank
    @UniqueValue(field = "email", domainClass = Usuario.class)
    private String email;

    @NotBlank
    @Size(min=6)
    private String senha;

    public UsuarioForm(String email, String senha){
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public Usuario toModel(){
        return new Usuario(this.email, encode(this.senha));
    }

    private String encode(String senha){
        return new BCryptPasswordEncoder().encode(senha);
    }





}

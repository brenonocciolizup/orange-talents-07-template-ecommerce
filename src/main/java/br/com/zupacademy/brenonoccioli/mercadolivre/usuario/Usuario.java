package br.com.zupacademy.brenonoccioli.mercadolivre.usuario;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min=6)
    private String senhaEncodada;

    @NotNull
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @Deprecated
    public Usuario() {
    }

    public Usuario(String email, String senha) {
        Assert.hasLength(email, "email não pode ser vazio");
        Assert.notNull(email, "email não pode ser nulo");
        Assert.isTrue(senha.length() >= 6, "senha precisa ter no mínimo 6 caracteres");
        this.email = email;
        //Já recebe a senha encodada
        this.senhaEncodada = senha;
    }

    public String getEmail() {
        return email;
    }

    public String getSenhaEncodada() {
        return senhaEncodada;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
}

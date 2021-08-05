package br.com.zupacademy.brenonoccioli.mercadolivre.config.seguranca;

import br.com.zupacademy.brenonoccioli.mercadolivre.usuario.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.List;

public class UsuarioLogado implements UserDetails{

    private Usuario usuario;
    private User springUserDetails;

    public UsuarioLogado(Usuario usuario) {
        this.usuario = usuario;
        springUserDetails = new User(usuario.getEmail(), usuario.getSenhaEncodada(), List.of());
    }

    public Collection<GrantedAuthority> getAuthorities() {
        return springUserDetails.getAuthorities();
    }

    public String getPassword() {
        return springUserDetails.getPassword();
    }

    public String getUsername() {
        return springUserDetails.getUsername();
    }

    public boolean isEnabled() {
        return springUserDetails.isEnabled();
    }

    public boolean isAccountNonExpired() {
        return springUserDetails.isAccountNonExpired();
    }

    public boolean isAccountNonLocked() {
        return springUserDetails.isAccountNonLocked();
    }

    public boolean isCredentialsNonExpired() {
        return springUserDetails.isCredentialsNonExpired();
    }

    public Usuario get() {
        return usuario;
    }
}


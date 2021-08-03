package br.com.zupacademy.brenonoccioli.mercadolivre.config.seguranca;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsMapper {
    UserDetails map(Object shouldBeASystemUser);
}
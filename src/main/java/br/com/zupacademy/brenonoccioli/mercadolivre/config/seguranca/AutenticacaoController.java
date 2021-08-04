package br.com.zupacademy.brenonoccioli.mercadolivre.config.seguranca;

import br.com.zupacademy.brenonoccioli.mercadolivre.config.seguranca.form.LoginForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenManager tokenManager;

    private static final Logger log = LoggerFactory
            .getLogger(AutenticacaoController.class);


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> authenticate(@RequestBody LoginForm loginInfo) {

        UsernamePasswordAuthenticationToken authenticationToken = loginInfo.build();

        try {
            Authentication authentication = authManager.authenticate(authenticationToken);
            String jwt = tokenManager.gerarToken(authentication);

            return ResponseEntity.ok(jwt);

        } catch (AuthenticationException e) {
            log.error("[Autenticacao] {}",e);
            return ResponseEntity.badRequest().build();
        }

    }
}

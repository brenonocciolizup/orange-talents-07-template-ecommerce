package br.com.zupacademy.brenonoccioli.mercadolivre.usuario;

import br.com.zupacademy.brenonoccioli.mercadolivre.usuario.dto.UsuarioDto;
import br.com.zupacademy.brenonoccioli.mercadolivre.usuario.dto.UsuarioForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioDto> cadastrar(@RequestBody @Valid UsuarioForm form){
        Usuario usuario = form.toModel();
        usuarioRepository.save(usuario);
        return ResponseEntity.ok().body(new UsuarioDto(usuario.getEmail(), usuario.getDataCriacao()));
    }
}

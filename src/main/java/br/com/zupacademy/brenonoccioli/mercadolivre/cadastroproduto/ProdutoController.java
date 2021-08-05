package br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto;

import br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto.dto.ProdutoDto;
import br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto.form.ProdutoForm;
import br.com.zupacademy.brenonoccioli.mercadolivre.categoria.CategoriaRepository;
import br.com.zupacademy.brenonoccioli.mercadolivre.config.seguranca.UsuarioLogado;
import br.com.zupacademy.brenonoccioli.mercadolivre.config.validacao.CaracteristicasNomeUnicoValidator;
import br.com.zupacademy.brenonoccioli.mercadolivre.usuario.Usuario;
import br.com.zupacademy.brenonoccioli.mercadolivre.usuario.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    CategoriaRepository categoriaRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    ProdutoRepository produtoRepository;


    @InitBinder
    public void init(WebDataBinder webDataBinder){
        webDataBinder.addValidators(new CaracteristicasNomeUnicoValidator());
    }


    @PostMapping("/cadastrar")
    public ResponseEntity<ProdutoDto> cadastrar(@RequestBody @Valid ProdutoForm form,
                                                @AuthenticationPrincipal UsuarioLogado usuarioLogado){
        Usuario dono = usuarioLogado.get();
        Produto produto = form.toModel(categoriaRepository, dono);
        produtoRepository.save(produto);

        return ResponseEntity.ok().body(new ProdutoDto(produto));

    }
}

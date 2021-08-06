package br.com.zupacademy.brenonoccioli.mercadolivre.adicionapergunta;

import br.com.zupacademy.brenonoccioli.mercadolivre.adicionapergunta.dto.PerguntaSobreProdutoDto;
import br.com.zupacademy.brenonoccioli.mercadolivre.adicionapergunta.form.PerguntaSobreProdutoForm;
import br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto.Produto;
import br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto.ProdutoRepository;
import br.com.zupacademy.brenonoccioli.mercadolivre.config.seguranca.UsuarioLogado;
import br.com.zupacademy.brenonoccioli.mercadolivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class PerguntaController {
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private EnviadorDeEmails disparadorDeEmail;

    @Autowired
    private PerguntaRepository perguntaRepository;

    @PostMapping("/produtos/{id}/pergunta")
    public ResponseEntity<PerguntaSobreProdutoDto> postarPergunta(@PathVariable("id") Long id,
                                                                  @RequestBody @Valid PerguntaSobreProdutoForm form,
                                                                  @AuthenticationPrincipal UsuarioLogado usuarioLogado){
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if(produtoOptional.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        Produto produto = produtoOptional.get();
        Usuario donoPergunta = usuarioLogado.get();

        if(donoPergunta.equals(produto.getDono())){
            return ResponseEntity.badRequest().build();
        }

        PerguntaSobreProduto pergunta = form.toModel(donoPergunta, produto);

        perguntaRepository.save(pergunta);
        disparadorDeEmail.enviaEmail(donoPergunta, produto.getDono());

        return ResponseEntity.ok().body(new PerguntaSobreProdutoDto(pergunta));
    }
}

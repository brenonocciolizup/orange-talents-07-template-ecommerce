package br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto;

import br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto.dto.DetalheProdutoDto;
import br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto.dto.ProdutoDto;
import br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto.form.ImagemProdutoForm;
import br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto.form.OpiniaoSobreProdutoForm;
import br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto.form.ProdutoForm;
import br.com.zupacademy.brenonoccioli.mercadolivre.categoria.CategoriaRepository;
import br.com.zupacademy.brenonoccioli.mercadolivre.config.seguranca.UsuarioLogado;
import br.com.zupacademy.brenonoccioli.mercadolivre.config.validacao.CaracteristicasNomeUnicoValidator;
import br.com.zupacademy.brenonoccioli.mercadolivre.usuario.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;


@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private UploaderImagens uploaderImagens;
    @Autowired
    private OpiniaoRepository opiniaoRepository;
    @Autowired
    private CalculadorDeNotas calculadorDeNotas;


    @InitBinder(value = "ProdutoForm")
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

    @PostMapping("/{id}/cadastrar-imagem")
    public ResponseEntity cadastraImagem(@PathVariable("id") Long id,
                                                  @Valid ImagemProdutoForm form,
                                                  @AuthenticationPrincipal UsuarioLogado usuarioLogado){
        //pegando links das imagens
        Set<String> links = uploaderImagens.enviar(form.getImagens());

        //pega produto passado na url para inserir a imagem
        Optional<Produto> produtoOptional = produtoRepository.findById(id);

       if(produtoOptional.isEmpty()){
            return ResponseEntity.badRequest().build();
        } else if (!produtoOptional.get().getDono().equals(usuarioLogado.get())){
           return ResponseEntity.status(403).build();
       }

        Produto produto = produtoOptional.get();
        produto.insereImagem(links);
        produtoRepository.save(produto);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/opiniao")
    public ResponseEntity postarOpiniao(@PathVariable("id") Long idProduto,
                              @RequestBody @Valid OpiniaoSobreProdutoForm form,
                              @AuthenticationPrincipal UsuarioLogado usuarioLogado){

        Optional<Produto> produtoOptional = produtoRepository.findById(idProduto);
        if(produtoOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Produto produto = produtoOptional.get();
        Usuario donoOpiniao = usuarioLogado.get();
        if(produto.getDono().equals(donoOpiniao)){
            return ResponseEntity.badRequest().body("Você não pode dar opinião em seu próprio produto");
        }

        OpiniaoSobreProduto opiniao = form.toModel(donoOpiniao, produto);
        produto.adicionaOpiniao(opiniao);
        opiniaoRepository.save(opiniao);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalheProdutoDto> detalhesDoProduto(@PathVariable("id") Long id){
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if(produtoOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Produto produto = produtoOptional.get();
        double mediaNotas = calculadorDeNotas.calculaMediaDeNotas(produto);

        return ResponseEntity.ok().body(new DetalheProdutoDto(produto, mediaNotas));
    }
}

package br.com.zupacademy.brenonoccioli.mercadolivre.finalizarcompra;

import br.com.zupacademy.brenonoccioli.mercadolivre.adicionapergunta.EnviadorDeEmails;
import br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto.Produto;
import br.com.zupacademy.brenonoccioli.mercadolivre.config.seguranca.UsuarioLogado;
import br.com.zupacademy.brenonoccioli.mercadolivre.finalizarcompra.form.CompraForm;
import br.com.zupacademy.brenonoccioli.mercadolivre.finalizarcompra.form.RetornoPagSeguroForm;
import br.com.zupacademy.brenonoccioli.mercadolivre.finalizarcompra.form.RetornoPaypalForm;
import br.com.zupacademy.brenonoccioli.mercadolivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;


@RestController
public class CompraController {
    @Autowired
    private EntityManager em;
    @Autowired
    private EnviadorDeEmails enviadorDeEmails;
    @Autowired
    private NotaFiscal notaFiscal;
    @Autowired
    private Ranking ranking;
    @Autowired
    private EventosNovaCompra eventosNovaCompra;

    @PostMapping("/compras")
    @Transactional
    public ResponseEntity<?> compraProduto(@RequestBody @Valid CompraForm form,
                                        @AuthenticationPrincipal UsuarioLogado usuarioLogado,
                                        UriComponentsBuilder uriComponentsBuilder) throws BindException, URISyntaxException {

        Produto produtoEmCompra = em.find(Produto.class, form.getIdProduto());
        int quantidade = form.getQuantidade();
        boolean estoqueAbatido = produtoEmCompra.abateEstoque(quantidade);

        if(estoqueAbatido){
            Usuario comprador = usuarioLogado.get();
            GatewayPagamento gateway = form.getGateway();

            Compra novaCompra = new Compra(produtoEmCompra, quantidade, comprador, gateway);
            //hibernate faz a sincornização do estado da classe produto
            em.persist(novaCompra);
            enviadorDeEmails.enviaEmail(comprador, produtoEmCompra.getDono());

            URI uri = new URI(novaCompra.redirecionamentoUrl(uriComponentsBuilder));
            return ResponseEntity.status(HttpStatus.FOUND).location(uri).build();
        }

        BindException estoqueInsuficiente = new BindException(form, "compraForm");
        estoqueInsuficiente.reject(null, "Compra não realizada por falta de estoque");

        throw estoqueInsuficiente;
    }

    @PostMapping("/retorno-pagseguro/{id}")
    @Transactional
    public String processaPagSeguro(@PathVariable("id") Long idCompra,
                                    @Valid RetornoPagSeguroForm form){
        return processa(idCompra, form);
    }

    @PostMapping("/retorno-paypal/{id}")
    @Transactional
    public String processaPaypal(@PathVariable("id") Long idCompra,
                                    @Valid RetornoPaypalForm form){
        return processa(idCompra, form);

    }

    private String processa(Long idCompra, RetornoGatewayPagamento retornoGatewayPagamento){
        Compra compra = em.find(Compra.class, idCompra);
        compra.adicionaTransacao(retornoGatewayPagamento);
        em.merge(compra);

        eventosNovaCompra.processa(compra);

        return compra.toString();
    }


}

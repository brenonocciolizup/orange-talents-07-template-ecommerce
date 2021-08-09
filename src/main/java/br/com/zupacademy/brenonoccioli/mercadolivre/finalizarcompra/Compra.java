package br.com.zupacademy.brenonoccioli.mercadolivre.finalizarcompra;

import br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto.Produto;
import br.com.zupacademy.brenonoccioli.mercadolivre.finalizarcompra.form.RetornoPagSeguroForm;
import br.com.zupacademy.brenonoccioli.mercadolivre.usuario.Usuario;
import org.springframework.util.Assert;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private Produto produtoEmCompra;

    @Positive
    private int quantidade;

    @NotNull
    @ManyToOne
    private Usuario comprador;

    @Enumerated
    @NotNull
    private GatewayPagamento gatewayPagamento;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private Set<Transacao> transacoes = new HashSet<>();

    @Deprecated
    public Compra() {
    }


    public Compra(Produto produtoEmCompra, int quantidade, Usuario comprador, GatewayPagamento gateway) {
        this.produtoEmCompra = produtoEmCompra;
        this.quantidade = quantidade;
        this.comprador = comprador;
        this.gatewayPagamento = gateway;
    }

    public Long getId() {
        return id;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public Usuario getDonoProduto() {
        return produtoEmCompra.getDono();
    }

    public String redirecionamentoUrl(
            UriComponentsBuilder uriComponentsBuilder) {
        return this.gatewayPagamento.urlRetorno(this, uriComponentsBuilder);
    }

    public void adicionaTransacao(RetornoGatewayPagamento form) {
        Transacao novaTransacao = form.toTransacao(this);
        Assert.state(!this.transacoes.contains(novaTransacao), "já existe transação igual");

        Assert.isTrue(transacoesConcluidasComSucesso().isEmpty(), "transação já foi concluída");

        this.transacoes.add(novaTransacao);
    }

    private Set<Transacao> transacoesConcluidasComSucesso() {
        Set<Transacao> transacoesConcluidasComSucesso = this.transacoes.stream()
                .filter(Transacao::concluidaComSucesso)
                .collect(Collectors.toSet());

        Assert.isTrue(transacoesConcluidasComSucesso.size() <= 1,
                "Mais de uma transacao concluida com sucesso " + this.id);

        return transacoesConcluidasComSucesso;
    }

    public boolean processadaComSucesso() {
        return !transacoesConcluidasComSucesso().isEmpty();
    }

    @Override
    public String toString() {
        return "Compra [id=" + id + ", produtoEscolhido=" + produtoEmCompra
                + ", quantidade=" + quantidade + ", comprador=" + comprador
                + ", gatewayPagamento=" + gatewayPagamento + ", transacoes=";

    }
}
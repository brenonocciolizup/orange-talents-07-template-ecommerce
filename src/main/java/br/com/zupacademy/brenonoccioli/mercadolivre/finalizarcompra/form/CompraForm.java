package br.com.zupacademy.brenonoccioli.mercadolivre.finalizarcompra.form;

import br.com.zupacademy.brenonoccioli.mercadolivre.annotations.IdExists;
import br.com.zupacademy.brenonoccioli.mercadolivre.annotations.QuantidadeValid;
import br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto.Produto;
import br.com.zupacademy.brenonoccioli.mercadolivre.finalizarcompra.GatewayPagamento;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@QuantidadeValid
public class CompraForm {

    @NotNull
    @IdExists(domainClass = Produto.class, field = "id")
    private Long idProduto;

    @NotNull
    @Positive
    private int quantidade;

    @NotNull
    private GatewayPagamento gateway;

    public CompraForm(Long idProduto, Integer quantidade, GatewayPagamento gatewayPagamento) {
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.gateway = gatewayPagamento;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public GatewayPagamento getGatewayPagamento() {
        return gateway;
    }
}

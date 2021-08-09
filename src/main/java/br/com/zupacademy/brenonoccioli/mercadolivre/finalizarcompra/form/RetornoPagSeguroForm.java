package br.com.zupacademy.brenonoccioli.mercadolivre.finalizarcompra.form;

import br.com.zupacademy.brenonoccioli.mercadolivre.finalizarcompra.Compra;
import br.com.zupacademy.brenonoccioli.mercadolivre.finalizarcompra.RetornoGatewayPagamento;
import br.com.zupacademy.brenonoccioli.mercadolivre.finalizarcompra.StatusPagamentoPagSeguro;
import br.com.zupacademy.brenonoccioli.mercadolivre.finalizarcompra.Transacao;

import javax.validation.constraints.NotNull;

public class RetornoPagSeguroForm implements RetornoGatewayPagamento {
    @NotNull
    private String idTransacao;
    @NotNull
    private StatusPagamentoPagSeguro status;

    public RetornoPagSeguroForm(String idTransacao, StatusPagamentoPagSeguro status){
        this.idTransacao = idTransacao;
        this.status = status;
    }

    public Transacao toTransacao(Compra compra) {
        return new Transacao(status.normaliza(), idTransacao, compra);
    }

    @Override
    public String toString() {
        return "RetornoPagseguroRequest [idTransacao=" + idTransacao
                + ", status=" + status + "]";
    }
}


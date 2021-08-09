package br.com.zupacademy.brenonoccioli.mercadolivre.finalizarcompra.form;

import br.com.zupacademy.brenonoccioli.mercadolivre.finalizarcompra.Compra;
import br.com.zupacademy.brenonoccioli.mercadolivre.finalizarcompra.RetornoGatewayPagamento;
import br.com.zupacademy.brenonoccioli.mercadolivre.finalizarcompra.StatusTransacao;
import br.com.zupacademy.brenonoccioli.mercadolivre.finalizarcompra.Transacao;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class RetornoPaypalForm implements RetornoGatewayPagamento {
    @Min(0)
    @Max(1)
    private int status;

    @NotBlank
    private String idTransacao;

    public RetornoPaypalForm(int status, String idTransacao) {
        this.status = status;
        this.idTransacao = idTransacao;
    }

    public Transacao toTransacao(Compra compra){
        StatusTransacao status = this.status == 0 ? StatusTransacao.erro : StatusTransacao.sucesso;

        return new Transacao(status, idTransacao, compra);
    }
}

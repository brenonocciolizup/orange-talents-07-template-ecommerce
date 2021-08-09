package br.com.zupacademy.brenonoccioli.mercadolivre.finalizarcompra;

public interface RetornoGatewayPagamento {
    Transacao toTransacao(Compra compra);
}

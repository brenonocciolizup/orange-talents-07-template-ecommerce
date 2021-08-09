package br.com.zupacademy.brenonoccioli.mercadolivre.finalizarcompra.form;

import javax.validation.constraints.NotNull;

public class RankingNovaCompraForm {

    @NotNull
    private Long idCompra;

    @NotNull
    private Long idVendedor;

    public RankingNovaCompraForm(Long idCompra, Long idVendedor) {
        this.idCompra = idCompra;
        this.idVendedor = idVendedor;
    }

    @Override
    public String toString() {
        return "RankingNovaCompraForm{" +
                "idCompra=" + idCompra +
                ", idVendedor=" + idVendedor +
                '}';
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public Long getIdVendedor() {
        return idVendedor;
    }
}



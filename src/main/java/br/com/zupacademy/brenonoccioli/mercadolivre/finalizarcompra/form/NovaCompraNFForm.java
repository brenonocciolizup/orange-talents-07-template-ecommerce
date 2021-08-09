package br.com.zupacademy.brenonoccioli.mercadolivre.finalizarcompra.form;


import javax.validation.constraints.NotNull;

public class NovaCompraNFForm {
    @NotNull
    private Long idCompra;

    @NotNull
    private Long idComprador;

    public NovaCompraNFForm(Long idCompra, Long idComprador) {
        this.idCompra = idCompra;
        this.idComprador = idComprador;
    }

    @Override
    public String toString() {
        return "NovaCompraNFForm{" +
                "idCompra=" + idCompra +
                ", idComprador=" + idComprador +
                '}';
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public Long getIdComprador() {
        return idComprador;
    }
}

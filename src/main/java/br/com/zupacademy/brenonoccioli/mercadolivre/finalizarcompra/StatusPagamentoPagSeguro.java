package br.com.zupacademy.brenonoccioli.mercadolivre.finalizarcompra;

public enum StatusPagamentoPagSeguro {
    sucesso, erro;

    public StatusTransacao normaliza() {
        if(this.equals(sucesso)) {
            return StatusTransacao.sucesso;
        }

        return StatusTransacao.erro;
    }
}

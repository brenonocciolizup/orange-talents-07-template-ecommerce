package br.com.zupacademy.brenonoccioli.mercadolivre.finalizarcompra;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private StatusTransacao status;

    @NotBlank
    private String idTransacaoGateway;

    private LocalDateTime instanteTransacao = LocalDateTime.now();

    @NotNull
    @ManyToOne
    private Compra compra;

    @Deprecated
    public Transacao(){}

    public Transacao(StatusTransacao status, String idTransacao, Compra compra){
        this.status = status;
        this.idTransacaoGateway = idTransacao;
        this.compra = compra;
    }

    public boolean concluidaComSucesso(){
        return this.status.equals(StatusTransacao.sucesso);
    }

    @Override
    public String toString() {
        return "Transacao [id=" + id + ", status=" + status
                + ", idTransacaoGateway=" + idTransacaoGateway + ", instante="
                + instanteTransacao + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idTransacaoGateway == null) ? 0 : idTransacaoGateway.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Transacao other = (Transacao) obj;
        if (idTransacaoGateway == null) {
            if (other.idTransacaoGateway != null)
                return false;
        } else if (!idTransacaoGateway.equals(other.idTransacaoGateway))
            return false;
        return true;
    }
}

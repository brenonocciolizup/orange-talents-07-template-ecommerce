package br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class CalculadorDeNotas {
    @Autowired
    private OpiniaoRepository opiniaoRepository;

    private List<Integer> notas;

    private List<Integer> pegaNotasPorProduto(Produto produto){
        List<Integer> listaNotas = new ArrayList<>();
        for (OpiniaoSobreProduto opiniao : produto.getOpinioes()) {
            listaNotas.add(opiniao.getNota());
        }
        return listaNotas;
    }

    public Double calculaMediaDeNotas(Produto produto) {
        List<Integer> listaNotas = pegaNotasPorProduto(produto);
        double total = 0;

        for (Integer nota : listaNotas) {
            total += nota;
        }

        double media = total/listaNotas.size();
        return media;

    }
}

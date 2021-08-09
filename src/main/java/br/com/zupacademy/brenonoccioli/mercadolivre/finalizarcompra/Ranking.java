package br.com.zupacademy.brenonoccioli.mercadolivre.finalizarcompra;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class Ranking implements EventoCompraSucesso{

    @Override
    public void processa(Compra compra){
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> form = Map.of("idCompra", compra.getId(), "idVendedor", compra.getDonoProduto().getId());

        restTemplate.postForEntity("localhost:8080/ranking", form, String.class);
    }
}

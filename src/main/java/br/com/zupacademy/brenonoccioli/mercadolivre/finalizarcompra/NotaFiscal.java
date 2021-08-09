package br.com.zupacademy.brenonoccioli.mercadolivre.finalizarcompra;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class NotaFiscal implements EventoCompraSucesso{

    @Override
    public void processa(Compra compra){
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> form = Map.of("idCompra", compra.getId(),
                "idComprador", compra.getComprador().getId());

        restTemplate.postForEntity("localhost:8080/notas-ficasis", form, String.class);
    }
}

package br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Component
public class UploaderImagens {


    public static Set<String> enviar(List<MultipartFile> imagens) {
        Set<String> listaLinks = new HashSet<>();
        for(MultipartFile img : imagens) {
            listaLinks.add("http://bucket.io/"+img.getOriginalFilename()
                    .replace(" ", ""));
        }
        return listaLinks;
    }
}

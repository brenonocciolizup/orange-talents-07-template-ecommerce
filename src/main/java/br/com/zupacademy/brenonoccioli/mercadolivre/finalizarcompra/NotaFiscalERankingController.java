package br.com.zupacademy.brenonoccioli.mercadolivre.finalizarcompra;

import br.com.zupacademy.brenonoccioli.mercadolivre.finalizarcompra.form.NovaCompraNFForm;
import br.com.zupacademy.brenonoccioli.mercadolivre.finalizarcompra.form.RankingNovaCompraForm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class NotaFiscalERankingController {
    @PostMapping("/notas-fiscais")
    public void criaNota(@Valid @RequestBody NovaCompraNFForm form) throws InterruptedException {
        System.out.println("criando nota fiscal para "+form);
        Thread.sleep(150);
    }

    @PostMapping("/ranking")
    public void ranking(@Valid @RequestBody RankingNovaCompraForm form) throws InterruptedException {
        System.out.println("criando ranking "+form);
        Thread.sleep(150);
    }
}

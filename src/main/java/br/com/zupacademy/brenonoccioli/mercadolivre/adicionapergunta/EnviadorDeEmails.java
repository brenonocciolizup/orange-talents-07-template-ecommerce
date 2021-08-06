package br.com.zupacademy.brenonoccioli.mercadolivre.adicionapergunta;

import br.com.zupacademy.brenonoccioli.mercadolivre.usuario.Usuario;
import org.springframework.stereotype.Component;

@Component
public class EnviadorDeEmails {

    public void enviaEmail(Usuario donoPergunta, Usuario donoProduto){
        System.out.println("E-mail enviado para "+donoProduto.getEmail()+
                " com a pergunta de " + donoPergunta.getEmail());
    }
}

package br.com.zupacademy.brenonoccioli.mercadolivre.adicionapergunta;

import br.com.zupacademy.brenonoccioli.mercadolivre.usuario.Usuario;
import org.springframework.stereotype.Component;

@Component
public class EnviadorDeEmails {

    public void enviaEmail(Usuario cliente, Usuario vendedor){
        System.out.println("E-mail enviado para "+vendedor.getEmail()+
                " com a pergunta de " + cliente.getEmail());
    }
}

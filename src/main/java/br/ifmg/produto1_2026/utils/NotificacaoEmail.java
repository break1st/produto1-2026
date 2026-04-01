package br.ifmg.produto1_2026.utils;

import br.ifmg.produto1_2026.entities.Usuario;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoEmail implements Notificador {

    private boolean caixaAlta;
    private String servidorSmpt;

    public NotificacaoEmail(String servidorSmpt) {
        System.out.println("Notificao email com sucesso");
        this.servidorSmpt = servidorSmpt;
    }

    public void notificar(Usuario usuario, String mensagem) {
        if (caixaAlta) {
            mensagem = mensagem.toUpperCase();
        }
        System.out.printf("Notificando %s através do email %s no servidor %s: %s\n", usuario.getNome(), usuario.getEmail(), servidorSmpt,mensagem);
    }
}

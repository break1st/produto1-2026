package br.ifmg.produto1_2026.services;

import br.ifmg.produto1_2026.entities.Usuario;
import br.ifmg.produto1_2026.utils.Notificador;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;

@Service
public class AtivacaoClienteService {

    private Notificador notificador;

    public AtivacaoClienteService(Notificador notificador) {
        System.out.println("Iniciando AtivacaoClienteService");
    }

    public void notificar(Usuario usuario, String mensagem) {
        notificador.notificar(usuario, mensagem);
    }

    @PostConstruct
    public void init() {
        System.out.println("Metodo execuado depois do construtor");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Metodo executado ao destruir o construtor");
    }
}
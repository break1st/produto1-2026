package br.ifmg.produto1_2026.services;

import br.ifmg.produto1_2026.anotacoes.TipoDoNotificador;
import br.ifmg.produto1_2026.constants.TipoDeNotificacao;
import br.ifmg.produto1_2026.entities.Usuario;
import br.ifmg.produto1_2026.utils.NotificacaoEmail;
import br.ifmg.produto1_2026.utils.Notificador;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //mesma coisa que @Component
public class AtivacaoClienteService {



    // @TipoDoNotificador(value = TipoDeNotificacao.SMS)
    // @Autowired   //<--- essa é uma forma de injetar o bean (forma 1)
    // private Notificador notificador;

    //private List<Notificador> notificadores;

    /*
        //forma (2)  no construtor inserir o bean como parâmetro
        @Autowired  //(forma 3)  quando existe overload de construtores
        public AtivacaoClienteService(List<Notificador> notificadores) {
            System.out.println(
                    "Iniciando AtivacaoClienteService");
            this.notificadores = notificadores;
        }
    */
    public AtivacaoClienteService() {
        System.out.println(
                "Iniciando AtivacaoClienteService com o construtor sem parâmetro");
    }


    public void ativar(Usuario usuario, String mensagem){
        //usuario.ativo(); simulando ativar o usuario.

        // if (notificador != null)
        //   notificador.notificar(usuario, mensagem);
        //for (Notificador notificador : notificadores) {
        //    notificador.notificar(usuario, mensagem);
        //}


    }

    @PostConstruct
    public void init(){
        System.out.println("Metodo executado depois do construtor");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("Metodo executado ao destruir o objeto");
    }

/*
    public Notificador getNotificador() {
        return notificador;
    }

    //@Autowired  (forma 4) - indicamos a injenção do objeto no
    //metodo set.
    public void setNotificador(Notificador notificador) {
        this.notificador = notificador;
    }

*/


}
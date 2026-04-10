package br.ifmg.produto1_2026.config;

import br.ifmg.produto1_2026.utils.NotificacaoEmail;
import br.ifmg.produto1_2026.utils.Notificador;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

//@Configurable
public class NotificadorConfig {

/*
    //nessa caso, a criação do bean é necessária,
    //pois o Spring Boot NÃO saberia criar esse objeto.
    //Qual seria o servidor smtp??
    @Bean
    public Notificador notificacaoEmail() {

        NotificacaoEmail notificacaoEmail
                = new NotificacaoEmail("smtp.google.com");
        notificacaoEmail.setCaixaAlta(true);

        return notificacaoEmail;
    }

 */
}
package br.ifmg.produto1_2026.config;

import br.ifmg.produto1_2026.services.AtivacaoClienteService;
import br.ifmg.produto1_2026.utils.NotificacaoEmail;
import br.ifmg.produto1_2026.utils.NotificacaoSMS;
import br.ifmg.produto1_2026.utils.Notificador;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ProdutosConfig {

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

    //@Primary -- desambigua beans, indicando qual objeto o
    //o spring boot deve usar.
    @Bean
    public Notificador notificacaoSMS() {

        NotificacaoSMS notificacaoSMS
                = new NotificacaoSMS();
        notificacaoSMS.setCaixaAlta(true);

        return notificacaoSMS;
    }


/*
    //nessa caso, a criação do bean não seria necessária,
    //pois o Spring Boot saberia criar esse objeto.
    @Bean
    public AtivacaoClienteService ativacaoClienteService(Notificador notificador) {
        AtivacaoClienteService  ativacaoClienteService
                = new AtivacaoClienteService(notificador);

        return ativacaoClienteService;

    }

*/


}
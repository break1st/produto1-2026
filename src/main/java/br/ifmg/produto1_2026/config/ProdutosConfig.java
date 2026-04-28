package br.ifmg.produto1_2026.config;

import br.ifmg.produto1_2026.anotacoes.TipoDoNotificador;
import br.ifmg.produto1_2026.constants.TipoDeNotificacao;
import br.ifmg.produto1_2026.service.AtivacaoClienteService;
import br.ifmg.produto1_2026.util.NotificacaoEmail;
import br.ifmg.produto1_2026.util.NotificacaoSMS;
import br.ifmg.produto1_2026.util.Notificador;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

//@Configuration
public class ProdutosConfig {
/*
    @Value("${notificador.email.host}")
    private String servidorSMTP;


    //nessa caso, a criação do bean é necessária,
    //pois o Spring Boot NÃO saberia criar esse objeto.
    //Qual seria o servidor smtp??
    @Profile("prod")
    @TipoDoNotificador(value = TipoDeNotificacao.EMAIL)
    @Bean
    public Notificador notificacaoEmail() {

        NotificacaoEmail notificacaoEmail
                = new NotificacaoEmail(servidorSMTP);
        notificacaoEmail.setCaixaAlta(true);

        return notificacaoEmail;
    }

    //@Primary -- desambigua beans, indicando qual objeto o
    //o spring boot deve usar.
    @Profile("dev")
    @TipoDoNotificador(value = TipoDeNotificacao.SMS)
    @Bean
    public Notificador notificacaoSMS() {

        NotificacaoSMS notificacaoSMS
                = new NotificacaoSMS();
        notificacaoSMS.setCaixaAlta(true);

        return notificacaoSMS;
    }
*/

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
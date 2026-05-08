package br.ifmg.produto1_2026.services;

import br.ifmg.produto1_2026.dto.EmailDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Value("${spring.mail.password}")
    private String senha;

    public void sendMail(@Valid EmailDTO emailDTO) {
        try {
            //Mensagem a ser enviada.
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo(emailDTO.getTo());
            message.setSubject(emailDTO.getSubject());
            message.setText(emailDTO.getBody());

            //"Enviador" de mensagens
            mailSender.send(message);
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }
}
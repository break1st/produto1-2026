package br.ifmg.produto1_2026.resources;

import br.ifmg.produto1_2026.dto.EmailDTO;
import br.ifmg.produto1_2026.services.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/email")
public class EmailResource {

    @Autowired
    private EmailService emailService;

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRADOR', 'ROLE_VENDEDOR', 'ROLE_CLIENTE')")
    @PostMapping
    public ResponseEntity<Void> sendEmail(@Valid @RequestBody EmailDTO emailDTO) {
        emailService.sendMail(emailDTO);
        return ResponseEntity.noContent().build();
    }
}
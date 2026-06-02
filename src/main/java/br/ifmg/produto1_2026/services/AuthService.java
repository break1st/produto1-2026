package br.ifmg.produto1_2026.services;

import br.ifmg.produto1_2026.dto.*;
import br.ifmg.produto1_2026.entities.PasswordRecover;
import br.ifmg.produto1_2026.entities.Perfil;
import br.ifmg.produto1_2026.entities.Usuario;
import br.ifmg.produto1_2026.repositories.PasswordRecoverRepository;
import br.ifmg.produto1_2026.repositories.PerfilRepository;
import br.ifmg.produto1_2026.repositories.UsuarioRepository;
import br.ifmg.produto1_2026.services.exceptions.ResourceNotFound;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private PerfilRepository perfilRepository;


    @Value("${spring.mail.username}")
    private String defaultSender;
    @Value("${email.password-recover.uri}")
    private String recoverUri;
    @Value("${email.password-recover.token.minutes}")
    private Long tokenMinutes;

    @Autowired
    private PasswordRecoverRepository passwordRecoverRepository;
    @Autowired
    private EmailService emailService;

    @Transactional
    public UsuarioDTO signup(UsuarioInsertDTO dto) {

        Usuario entity = new Usuario();

        dto.getPerfis().clear();
        Perfil cliente = perfilRepository.findByNome("ROLE_CLIENTE");
        dto.getPerfis().add( new PerfilDTO(cliente) );

        copyDtoToEntity(dto, entity);
        entity.setSenha(encoder.encode(dto.getSenha()));

        Usuario novo = repository.save(entity);
        return new UsuarioDTO(novo);
    }


    private void copyDtoToEntity(UsuarioDTO dto, Usuario entity) {
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        entity.setTelefone(dto.getTelefone());

        entity.getPerfis().clear();
        for (PerfilDTO perfilDto: dto.getPerfis()){
            Perfil perfil = perfilRepository.getReferenceById(perfilDto.getId());
            entity.getPerfis().add(perfil);
        }
    }

    public void createRecoverToken(@Valid RequestTokenDTO dto) {
        Usuario user = repository.findByEmail(dto.getEmail());
        if (user == null) {
            throw new ResourceNotFound("Email not found");
        }
        String token = UUID.randomUUID().toString();

        PasswordRecover entity = new PasswordRecover();
        entity.setToken(token);
        entity.setExpiration(Instant.now().plusSeconds(tokenMinutes * 60L));
        entity.setEmail(dto.getEmail());
        passwordRecoverRepository.save(entity);

        String text = "Acesse o link para definir uma nova senha (válido por "
                + tokenMinutes + " minutos):\n\n" + recoverUri + token;
        emailService.sendMail(new EmailDTO(dto.getEmail(), "Recuperação de senha", text));
    }
}
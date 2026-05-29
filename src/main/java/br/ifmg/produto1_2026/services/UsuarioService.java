package br.ifmg.produto1_2026.services;

import br.ifmg.produto1_2026.dto.PerfilDTO;
import br.ifmg.produto1_2026.dto.UsuarioDTO;
import br.ifmg.produto1_2026.dto.UsuarioInsertDTO;
import br.ifmg.produto1_2026.entities.Perfil;
import br.ifmg.produto1_2026.entities.Usuario;
import br.ifmg.produto1_2026.projections.UserDetailsProjection;
import br.ifmg.produto1_2026.repositories.PerfilRepository;
import br.ifmg.produto1_2026.repositories.UsuarioRepository;
import br.ifmg.produto1_2026.services.exceptions.ErroNoBancoDeDados;
import br.ifmg.produto1_2026.services.exceptions.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Transactional(readOnly = true)
    public UsuarioDTO findById(Long id) {
        return usuarioRepository.findById(id).map(UsuarioDTO::new)
                .orElseThrow(() -> new ResourceNotFound("Usuario não encontrada"));
    }

    @Transactional(readOnly = true)
    public Page<UsuarioDTO> findAll(Pageable pageable) {
         return usuarioRepository.findAll(pageable)
             .map(UsuarioDTO::new);
    }

    @Transactional
    public UsuarioDTO insert(UsuarioInsertDTO dto) {
        Usuario usuario = new Usuario();
        copyDtoToEntity(dto, usuario);
        usuario.setSenha(encoder.encode(dto.getSenha()));

        usuario = usuarioRepository.save(usuario);
        return new UsuarioDTO(usuario);
    }

    @Transactional
    public void delete(Long id) {
        if(!usuarioRepository.existsById(id)){
            throw new ResourceNotFound("Usuario não encontrada");
        }

        try {
            usuarioRepository.deleteById(id);
        }
        catch(DataIntegrityViolationException e) {
            throw new ErroNoBancoDeDados(e.getMessage());
        }
    }

    @Transactional
    public UsuarioDTO update(Long id, UsuarioDTO dto) {
        if(!usuarioRepository.existsById(id)){
            throw new ResourceNotFound("Usuario não encontrada");
        }

        Usuario usuario = usuarioRepository.getReferenceById(id);
        copyDtoToEntity(dto, usuario);
        usuario = usuarioRepository.save(usuario);
        return new UsuarioDTO(usuario);
    }

    // 18 - avançando com o spring security
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDetailsProjection> dados = usuarioRepository.loadUserByUsername(username);
        if (dados.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }

        return new Usuario();
    }

    private void copyDtoToEntity(UsuarioDTO dto, Usuario entity) {
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        entity.setTelefone(dto.getTelefone());

        entity.getPerfis().clear();
        for (PerfilDTO perfilDto: dto.getPerfis()){

            Perfil perfil =
                    perfilRepository
                            .getReferenceById(perfilDto.getId());
            entity.getPerfis().add(perfil);
        }
    }
}
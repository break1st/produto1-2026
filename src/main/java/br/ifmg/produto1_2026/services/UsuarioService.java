package br.ifmg.produto1_2026.services;

import br.ifmg.produto1_2026.dto.PerfilDTO;
import br.ifmg.produto1_2026.dto.UsuarioDTO;
import br.ifmg.produto1_2026.entities.Perfil;
import br.ifmg.produto1_2026.entities.Usuario;
import br.ifmg.produto1_2026.repositories.PerfilRepository;
import br.ifmg.produto1_2026.repositories.UsuarioRepository;
import br.ifmg.produto1_2026.services.exceptions.ErroNoBancoDeDados;
import br.ifmg.produto1_2026.services.exceptions.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

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
    public UsuarioDTO insert(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setTelefone(dto.getTelefone());

        for(PerfilDTO perfilDto: dto.getPerfis()){
            Perfil perfil = perfilRepository.getReferenceById(perfilDto.getId());
            usuario.getPerfis().add(perfil);
        }

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
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setTelefone(dto.getTelefone());
        usuario = usuarioRepository.save(usuario);
        return new UsuarioDTO(usuario);
    }
}
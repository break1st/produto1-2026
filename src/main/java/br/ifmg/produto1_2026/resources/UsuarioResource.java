package br.ifmg.produto1_2026.resources;


import br.ifmg.produto1_2026.dto.UsuarioDTO;
import br.ifmg.produto1_2026.dto.UsuarioInsertDTO;
import br.ifmg.produto1_2026.services.UsuarioService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {

    @Autowired
    private UsuarioService usuarioService;

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRADOR')")
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(usuarioService.findById(id));
    };

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRADOR')")
    @GetMapping
    public ResponseEntity<Page<UsuarioDTO>> findAll(
//            @RequestParam(value = "page", defaultValue = "0") Integer page,
//            @RequestParam(value = "linerPerPage", defaultValue = "10") Integer linerPerPage,
//            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
//            @RequestParam(value = "sort", defaultValue = "id") String sort
            Pageable pageable
    ) {
        return ResponseEntity.ok().body(usuarioService.findAll(pageable));
    };

    @PostMapping
    public ResponseEntity<UsuarioDTO> insert(
            @RequestBody @Valid UsuarioInsertDTO dto
    ){
        UsuarioDTO categoria = usuarioService.insert(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoria.getId())
                .toUri();

        return ResponseEntity.created(location).body(categoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> update(
            @PathVariable Long id, @RequestBody @Valid UsuarioDTO dto
    ) {
        UsuarioDTO categoria = usuarioService.update(id, dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoria.getId())
                .toUri();

        return ResponseEntity.created(location).body(categoria);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRADOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<UsuarioDTO> delete(@PathVariable Long id){
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
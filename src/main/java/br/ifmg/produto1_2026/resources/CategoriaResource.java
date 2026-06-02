package br.ifmg.produto1_2026.resources;

import br.ifmg.produto1_2026.dto.CategoriaDTO;
import br.ifmg.produto1_2026.services.CategoriaService;

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
@RequestMapping("/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(categoriaService.findById(id));
    };

    @GetMapping
    public ResponseEntity<Page<CategoriaDTO>> findAll(
//            @RequestParam(value = "page", defaultValue = "0") Integer page,
//            @RequestParam(value = "linerPerPage", defaultValue = "10") Integer linerPerPage,
//            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
//            @RequestParam(value = "sort", defaultValue = "id") String sort
        Pageable pageable
    ) {
        return ResponseEntity.ok().body(categoriaService.findAll(pageable));
    };

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRADOR', 'ROLE_VENDEDOR')")
    @PostMapping
    public ResponseEntity<CategoriaDTO> insert(@RequestBody CategoriaDTO dto){
        CategoriaDTO categoria = categoriaService.insert(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoria.getId())
                .toUri();

        return ResponseEntity.created(location).body(categoria);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRADOR', 'ROLE_VENDEDOR')")
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> update(@PathVariable Long id, @RequestBody CategoriaDTO dto) {
        CategoriaDTO categoria = categoriaService.update(id, dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoria.getId())
                .toUri();

        return ResponseEntity.created(location).body(categoria);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRADOR', 'ROLE_VENDEDOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<CategoriaDTO> delete(@PathVariable Long id){
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
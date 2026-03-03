package br.ifmg.produto1_2026.resources;

import br.ifmg.produto1_2026.entities.Categoria;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaResource {

    @GetMapping
    public ResponseEntity<List<Categoria>> categoria() {
        List<Categoria> categorias = new ArrayList<>();
        categorias.add(new Categoria(1L, "notebook"));
        categorias.add(new Categoria(2L, "celular"));
        categorias.add(new Categoria(3L, "livro"));

        return ResponseEntity.ok().body(categorias);
    };
}

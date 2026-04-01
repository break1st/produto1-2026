package br.ifmg.produto1_2026.resources;

import br.ifmg.produto1_2026.entities.Usuario;
import br.ifmg.produto1_2026.services.AtivacaoClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vendas")
public class VendaResource {

    @Autowired
    private AtivacaoClienteService ativacaoClienteService;

    @PostMapping
    public ResponseEntity<String> insert(){
        Usuario usuario = new Usuario();
        usuario.setNome("Nome");
        usuario.setTelefone("Telefone");
        usuario.setEmail("Email");
        ativacaoClienteService.notificar(usuario, "ativando...");

        return ResponseEntity.ok("OK");
    }
}
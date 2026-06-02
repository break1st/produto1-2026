package br.ifmg.produto1_2026.resources;


import br.ifmg.produto1_2026.dto.ProdutoDTO;
import br.ifmg.produto1_2026.dto.ProdutoListDTO;
import br.ifmg.produto1_2026.services.ProdutoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/produtos")
@Tag(name="Produtos", description = "Essa API é responsável por gerenciar produtos na plataforma.")
public class ProdutoResource {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping(value = "/{id}", produces = "application/json")
    @Operation(
            summary = "Endpoint retornar todos os produto",
            description = "A plataforma precisa disponibilibiar uma listagem de produtos....",
            responses = {
                    @ApiResponse(description = "Retorna a informação pesquisada por ID", responseCode = "200"),
                    @ApiResponse(description = "Infomação não encontrada.", responseCode = "404"),
            }
    )
    public ResponseEntity<ProdutoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(produtoService.findById(id));
    };

    @GetMapping(produces = "application/json")
    @Operation(
            summary = "Endpoint retornar todos os produto",
            description = "A plataforma precisa disponibilibiar uma listagem de produtos....",
            responses = {
                    @ApiResponse(description = "Lista retornada com sucesso", responseCode = "200"),
                    @ApiResponse(description = "Erro interno", responseCode = "500"),
            }
    )
    public ResponseEntity<Page<ProdutoListDTO>> produtos(
            @RequestParam(value="categoriasId", defaultValue = "0") String categoriasId,
            @RequestParam(value="name", defaultValue = "") String name,
            Pageable pageable){
        Page<ProdutoListDTO> produtos = produtoService.findAll(categoriasId, name, pageable);
        return ResponseEntity.ok().body(produtos);
    };

    @GetMapping(value = "/v1/", produces = "application/json")
    @Operation(
            summary = "Endpoint retornar todos os produto",
            description = "A plataforma precisa disponibilibiar uma listagem de produtos....",
            responses = {
                    @ApiResponse(description = "Lista retornada com sucesso", responseCode = "200"),
                    @ApiResponse(description = "Erro interno", responseCode = "500"),
            }
    )
    public ResponseEntity<Page<ProdutoDTO>> findAll(
//            @RequestParam(value = "page", defaultValue = "0") Integer page,
//            @RequestParam(value = "linerPerPage", defaultValue = "10") Integer linerPerPage,
//            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
//            @RequestParam(value = "sort", defaultValue = "id") String sort
            Pageable pageable
    ) {
        return ResponseEntity.ok().body(produtoService.findAll(pageable));
    };

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRADOR', 'ROLE_VENDEDOR')")
    @PostMapping(produces = "application/json")
    @Operation(
            summary = "Endpoint para inserir um produto",
            description = "A plataforma precisa disponibilibiar um cadastro e produtos....",
            responses = {
                    @ApiResponse(description = "Registro Criado", responseCode = "201"),
                    @ApiResponse(description = "Requisição mal-feita", responseCode = "400", content = {}),
                    @ApiResponse(description = "Não autorizado", responseCode = "401"),
                    @ApiResponse(description = "Proibido no seu perfil", responseCode = "403"),
                    @ApiResponse(description = "Erro ao processar", responseCode = "422"),
                    @ApiResponse(description = "Erro interno no servidor", responseCode = "500"),
            }
    )
    public ResponseEntity<ProdutoDTO> insert(@RequestBody @Valid ProdutoDTO dto){
        ProdutoDTO categoria = produtoService.insert(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoria.getId())
                .toUri();

        return ResponseEntity.created(location).body(categoria);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRADOR', 'ROLE_VENDEDOR')")
    @PutMapping(value="/{id}", produces = "application/json")
    @Operation(
            summary = "Endpoint para atualizar um produto",
            description = "A plataforma precisa disponibilibiar um cadastro e produtos....",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Requisição mal-feita", responseCode = "400", content = {}),
                    @ApiResponse(description = "Não autorizado", responseCode = "401"),
                    @ApiResponse(description = "Proibido no seu perfil", responseCode = "403"),
                    @ApiResponse(description = "Não encontrado", responseCode = "404"),
                    @ApiResponse(description = "Erro ao processar", responseCode = "422"),
                    @ApiResponse(description = "Erro interno no servidor", responseCode = "500"),
            }
    )
    public ResponseEntity<ProdutoDTO> update(@PathVariable Long id, @RequestBody ProdutoDTO dto) {
        ProdutoDTO categoria = produtoService.update(id, dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoria.getId())
                .toUri();

        return ResponseEntity.created(location).body(categoria);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRADOR')")
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Endpoint para apagar um produto",
            description = "A plataforma precisa disponibilibiar um cadastro e produtos....",
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "204"),
                    @ApiResponse(description = "Requisição mal-feita", responseCode = "400", content = {}),
                    @ApiResponse(description = "Não autorizado", responseCode = "401"),
                    @ApiResponse(description = "Proibido no seu perfil", responseCode = "403"),
                    @ApiResponse(description = "Não encontrado", responseCode = "404"),
                    @ApiResponse(description = "Erro ao processar", responseCode = "422"),
                    @ApiResponse(description = "Erro interno no servidor", responseCode = "500"),
            }
    )
    public ResponseEntity<ProdutoDTO> delete(@PathVariable Long id){
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
package br.ifmg.produto1_2026.dto;

import br.ifmg.produto1_2026.entities.Categoria;

public class PerfilDTO {

    private long id;
    private String nome;

    public PerfilDTO(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
    }

    public PerfilDTO() {}

    public PerfilDTO(long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

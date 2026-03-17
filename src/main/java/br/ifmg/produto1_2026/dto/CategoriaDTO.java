package br.ifmg.produto1_2026.dto;

import br.ifmg.produto1_2026.entities.Categoria;

import java.time.Instant;

public class CategoriaDTO {

    private long id;
    private String nome;
    private Instant criadoEm;
    private Instant atualizadoEm;

    public CategoriaDTO(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
        this.criadoEm = categoria.getCriadoEm();
        this.atualizadoEm = categoria.getAtualizadoEm();
    }

    public CategoriaDTO() {}

    public CategoriaDTO(long id, String nome) {
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

    public Instant getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(Instant criadoEm) {
        this.criadoEm = criadoEm;
    }

    public Instant getAtualizadoEm() {
        return atualizadoEm;
    }

    public void setAtualizadoEm(Instant atualizadoEm) {
        this.atualizadoEm = atualizadoEm;
    }
}

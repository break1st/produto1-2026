package br.ifmg.produto1_2026.entities;


import jakarta.persistence.*;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "tb_produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private Double preco;
    private String imgUrl;


    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant atualizadoEm;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant criadoEm;

    public Produto(Long id, String nome, String descricao, Double preco, String imgUrl) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.imgUrl = imgUrl;
        this.criadoEm = Instant.now();
    }

    public Produto(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Instant getAtualizadoEm() { return atualizadoEm; }

    public Instant getCriadoEm() { return criadoEm; }

    @PrePersist
    public void prePersist() {
        this.criadoEm = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.atualizadoEm = Instant.now();
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", imgUrl='" + imgUrl + '\'' +
                ", atualizadoEm=" + atualizadoEm +
                ", criadoEm=" + criadoEm +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Produto Produto = (Produto) o;
        return Objects.equals(id, Produto.id) && Objects.equals(nome, Produto.nome) && Objects.equals(descricao, Produto.descricao) && Objects.equals(preco, Produto.preco) && Objects.equals(imgUrl, Produto.imgUrl) && Objects.equals(atualizadoEm, Produto.atualizadoEm) && Objects.equals(criadoEm, Produto.criadoEm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, descricao, preco, imgUrl, atualizadoEm, criadoEm);
    }
}
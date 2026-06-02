package br.ifmg.produto1_2026.dto;

import br.ifmg.produto1_2026.entities.Produto;
import br.ifmg.produto1_2026.projections.ProdutoProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

public class ProdutoListDTO extends RepresentationModel<ProdutoListDTO> {

    @Schema(description = "identificador único no sistema")
    private Long id;
    @Schema(description = "nome do produto")
    @Size(min = 2, max = 100 , message = "O nome do produto deve ter entre 2 e 100 caractres")
    private String nome;
    @Schema(description = "valor em reais do produto")
    @Positive(message = "O preço do produto deve ser positivo")
    private Double preco;
    @NotNull
    @Schema(description = "endereço eletrônico da imagem")
    private String imgUrl;



    public ProdutoListDTO() {
    }

    public ProdutoListDTO(Long id, String nome, String descricao, Double preco, String imgUrl) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.imgUrl = imgUrl;
    }

    public ProdutoListDTO(Produto entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.preco = entity.getPreco();
        this.imgUrl = entity.getImgUrl();
    }

    public ProdutoListDTO(ProdutoProjection projection) {
        this.id = projection.getID();
        this.nome = projection.getNome();
        this.preco = projection.getPreco();
        this.imgUrl = projection.getImgUrl();
    }

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


    @Override
    public String toString() {
        return "ProdutoDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
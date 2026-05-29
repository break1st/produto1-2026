package br.ifmg.produto1_2026.dto;

import br.ifmg.produto1_2026.entities.Produto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDTO extends RepresentationModel<ProdutoDTO> {

    @Schema(description = "identificador único no sistema")
    private Long id;
    @Schema(description = "nome do produto")
    @Size(min = 2, max = 100 , message = "O nome do produto deve ter entre 2 e 100 caractres")
    private String nome;
    @Schema(description = "descricao detalhada do produto")
    private String descricao;
    @Schema(description = "valor em reais do produto")
    @Positive(message = "O preço do produto deve ser positivo")
    private Double preco;
    @Schema(description = "endereço eletrônico da imagem")
    private String imgUrl;

    @Schema(description = "lista das categorias que o produto pertence.")
    private List<CategoriaDTO> categorias = new ArrayList();

    public ProdutoDTO(Long id, String nome, String descricao, Double preco, String imgUrl) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.imgUrl = imgUrl;
    }

    public ProdutoDTO(){}

    public ProdutoDTO(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.preco = produto.getPreco();
        this.imgUrl = produto.getImgUrl();
        produto
            .getCategorias()
            .forEach(c -> this.categorias.add(new CategoriaDTO(c)));
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

    public List<CategoriaDTO> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategoriaDTO> categorias) {
        this.categorias = categorias;
    }

    @Override
    public String toString() {
        return "ProdutoDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
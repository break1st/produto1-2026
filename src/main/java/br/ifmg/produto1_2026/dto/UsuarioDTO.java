package br.ifmg.produto1_2026.dto;

import br.ifmg.produto1_2026.entities.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UsuarioDTO {

    private long id;
    @NotBlank(message = "Campo nome obrigatório")
    private String nome;
    private String telefone;
    @NotBlank(message = "Email obrigatório")
    @Email(message = "Email inválido")
    private String email;
    private List<PerfilDTO> perfis;

    public UsuarioDTO() {
    }

    public UsuarioDTO(long id, String nome, String telefone, String email, Instant dataCriacao, Instant dataAtualizacao) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.telefone = usuario.getTelefone();
        this.email = usuario.getEmail();
        this.perfis = new ArrayList<>();
        usuario.getPerfis().forEach(p -> this.perfis.add(new PerfilDTO(p)));
    }


    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<PerfilDTO> getPerfis() {
        return perfis;
    }

    public void setPerfis(List<PerfilDTO> perfis) {
        this.perfis = perfis;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioDTO user = (UsuarioDTO) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
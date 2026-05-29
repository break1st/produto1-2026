package br.ifmg.produto1_2026.dto;

public class UsuarioInsertDTO extends UsuarioDTO {

    private String senha;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + getId() +
                ", nome='" + getNome() + '\'' +
                ", telefone='" + getId() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", senha='" + getSenha() + '\'' +
                '}';
    }
}
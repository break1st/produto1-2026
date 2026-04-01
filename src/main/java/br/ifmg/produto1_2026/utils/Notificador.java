package br.ifmg.produto1_2026.utils;

import br.ifmg.produto1_2026.entities.Usuario;

public interface Notificador {
    void notificar(Usuario usuario, String mensagem);
}

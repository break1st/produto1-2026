package br.ifmg.produto1_2026.services.exceptions;

public class ResourceNotFound extends RuntimeException {

    public ResourceNotFound() {}

    public ResourceNotFound(String message) {
        super(message);
    }
}

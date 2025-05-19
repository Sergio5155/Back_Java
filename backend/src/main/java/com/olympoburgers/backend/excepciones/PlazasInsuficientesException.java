package com.olympoburgers.backend.excepciones;

public class PlazasInsuficientesException extends RuntimeException {
    public PlazasInsuficientesException(String mensaje) {
        super(mensaje);
    }
}

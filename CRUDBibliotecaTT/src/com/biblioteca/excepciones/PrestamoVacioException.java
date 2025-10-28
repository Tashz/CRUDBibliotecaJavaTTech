package com.biblioteca.excepciones;

public class PrestamoVacioException extends Exception {
    public PrestamoVacioException(String mensaje) {
        super(mensaje);
    }
}
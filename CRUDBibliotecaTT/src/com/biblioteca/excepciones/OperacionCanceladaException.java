package com.biblioteca.excepciones;

public class OperacionCanceladaException extends Exception {
    public OperacionCanceladaException(String mensaje) {
        super(mensaje);
    }
}

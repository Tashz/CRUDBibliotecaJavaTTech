package com.biblioteca.prestamos;

import com.biblioteca.libros.Libro;

public class LineaPrestamo {
    private Libro libro;
    private int cantidad;

    public LineaPrestamo(Libro libro, int cantidad) {
        this.libro = libro;
        this.cantidad = cantidad;
    }

    public Libro getLibro() { return libro; }
    public int getCantidad() { return cantidad; }

    @Override
    public String toString() {
        return libro.getTitulo() + " x " + cantidad;
    }

}

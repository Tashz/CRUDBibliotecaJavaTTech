package com.biblioteca.libros;

public class Libro {
    private static int contador = 1;
    private int id;
    private int anio;
    private int stock;
    private String titulo;
    private String autor;
    private String genero;

    public Libro(String titulo, String autor, String genero, int stock, int anio) {
        this.id = contador++;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.stock = stock;
        this.anio = anio;
    }

    public int getId() { return id; }
    public int getStock() { return stock; }
    public int getAnio() { return anio; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getGenero() { return genero; }

    public void setStock(int stock) {this.stock = stock; }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    @Override
    public String toString() {
        return "Libro #" + id + " - " + titulo + " (" + anio + ")\n" +
                "Autor: " + autor + " | Género: " + genero + " | Stock: " + stock;
    }

}


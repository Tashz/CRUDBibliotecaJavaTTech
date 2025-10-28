package com.biblioteca.prestamos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Prestamo {
    private static int contador = 1;
    private int id;
    private List<LineaPrestamo> lineas;
    private LocalDate fecha;

    public Prestamo() {
        this.id = contador++;
        this.lineas = new ArrayList<>();
        this.fecha = LocalDate.now();
    }

    public void agregarLinea(LineaPrestamo linea) {
        lineas.add(linea);
    }

    public List<LineaPrestamo> getLineas() { return lineas; }
    public LocalDate getFecha() { return fecha; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Préstamo #").append(id).append(" - ").append(fecha).append("\n");
        for (LineaPrestamo lp : lineas) {
            sb.append("  ").append(lp).append("\n");
        }
        return sb.toString();
    }
}
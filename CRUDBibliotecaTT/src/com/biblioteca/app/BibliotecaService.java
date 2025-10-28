package com.biblioteca.app;

import com.biblioteca.libros.Libro;
import com.biblioteca.prestamos.LineaPrestamo;
import com.biblioteca.prestamos.Prestamo;
import com.biblioteca.excepciones.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BibliotecaService {
    private List<Libro> libros = new ArrayList<>();
    private List<Prestamo> prestamos = new ArrayList<>();

    public void agregarLibro(Scanner scanner) {
        try {
            System.out.print("Título: ");
            String titulo = scanner.nextLine();
            System.out.print("Autor: ");
            String autor = scanner.nextLine();

            buscarLibroPorTituloAutor(titulo, autor); // lanza DuplicadoException si ya existe

            System.out.print("Género: ");
            String genero = scanner.nextLine();
            System.out.print("Stock: ");
            int stock = scanner.nextInt();
            System.out.print("Año de publicación: ");
            int anio = scanner.nextInt();
            scanner.nextLine();

            if (stock < 0 || anio < 1400 || anio > LocalDate.now().getYear()) {
                throw new EntradaInvalidaException("Datos inválidos: stock o año fuera de rango.");
            }

            Libro libro = new Libro(titulo, autor, genero, stock, anio);
            libros.add(libro);
            System.out.println("Libro agregado con éxito.");

        } catch (DuplicadoException | EntradaInvalidaException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void listarLibros() {
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
            return;
        }
        for (Libro libro : libros) {
            System.out.println(libro);
        }
    }

    public void actualizarLibro(Scanner scanner) {
        try {
            System.out.print("ID del libro a actualizar: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            Libro libro = buscarLibroPorId(id);

            System.out.print("Nuevo título (actual: " + libro.getTitulo() + "): ");
            String nuevoTitulo = scanner.nextLine();
            System.out.print("Nuevo autor (actual: " + libro.getAutor() + "): ");
            String nuevoAutor = scanner.nextLine();
            System.out.print("Nuevo género (actual: " + libro.getGenero() + "): ");
            String nuevoGenero = scanner.nextLine();
            System.out.print("Nuevo stock (actual: " + libro.getStock() + "): ");
            int nuevoStock = scanner.nextInt();
            System.out.print("Nuevo año (actual: " + libro.getAnio() + "): ");
            int nuevoAnio = scanner.nextInt();
            scanner.nextLine();

            if (nuevoStock < 0 || nuevoAnio < 1400 || nuevoAnio > LocalDate.now().getYear()) {
                throw new EntradaInvalidaException("Datos inválidos: stock o año fuera de rango.");
            }

            confirmarOperacion(scanner);

            libro.setTitulo(nuevoTitulo);
            libro.setAutor(nuevoAutor);
            libro.setGenero(nuevoGenero);
            libro.setStock(nuevoStock);
            libro.setAnio(nuevoAnio);

            System.out.println("Libro actualizado con éxito.");

        } catch (LibroNoEncontradoException | EntradaInvalidaException | OperacionCanceladaException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void eliminarLibro(Scanner scanner) {
        try {
            System.out.print("ID del libro a eliminar: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            Libro libro = buscarLibroPorId(id);
            System.out.println("Libro a eliminar:");
            System.out.println(libro);

            confirmarOperacion(scanner);

            libros.remove(libro);
            System.out.println("Libro eliminado con éxito.");

        } catch (LibroNoEncontradoException | OperacionCanceladaException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void crearPrestamo(Scanner scanner) {
        Prestamo prestamo = new Prestamo();
        boolean agregar = true;

        while (agregar) {
            try {
                System.out.print("ID del libro a prestar: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                Libro libro = buscarLibroPorId(id);

                System.out.print("Cantidad: ");
                int cantidad = scanner.nextInt();
                scanner.nextLine();

                if (cantidad <= 0) {
                    throw new EntradaInvalidaException("La cantidad debe ser mayor a cero.");
                }

                if (libro.getStock() < cantidad) {
                    throw new StockInsuficienteException("Stock insuficiente para el libro: " + libro.getTitulo());
                }

                libro.setStock(libro.getStock() - cantidad);
                prestamo.agregarLinea(new LineaPrestamo(libro, cantidad));

                System.out.print("¿Agregar otro libro al préstamo? (s/n): ");
                String respuesta = scanner.nextLine();
                agregar = respuesta.equalsIgnoreCase("s");

            } catch (LibroNoEncontradoException | StockInsuficienteException | EntradaInvalidaException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        try {
            if (prestamo.getLineas().isEmpty()) {
                throw new PrestamoVacioException("No se puede registrar un préstamo vacío.");
            }

            prestamos.add(prestamo);
            System.out.println("Préstamo registrado:");
            System.out.println(prestamo);

        } catch (PrestamoVacioException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void listarPrestamos() {
        if (prestamos.isEmpty()) {
            System.out.println("No hay préstamos registrados.");
            return;
        }
        for (Prestamo p : prestamos) {
            System.out.println(p);
        }
    }

    // 🔍 Métodos auxiliares

    private Libro buscarLibroPorId(int id) throws LibroNoEncontradoException {
        for (Libro libro : libros) {
            if (libro.getId() == id) return libro;
        }
        throw new LibroNoEncontradoException("No se encontró el libro con ID: " + id);
    }

    private void confirmarOperacion(Scanner scanner) throws OperacionCanceladaException {
        System.out.print("¿Confirmar operación? (s/n): ");
        String respuesta = scanner.nextLine();
        if (!respuesta.equalsIgnoreCase("s")) {
            throw new OperacionCanceladaException("Operación cancelada por el usuario.");
        }
    }

    private void buscarLibroPorTituloAutor(String titulo, String autor) throws DuplicadoException {
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo) &&
                    libro.getAutor().equalsIgnoreCase(autor)) {
                throw new DuplicadoException("Ya existe un libro con ese título y autor.");
            }
        }
    }
}

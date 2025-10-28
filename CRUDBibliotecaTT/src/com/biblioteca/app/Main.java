package com.biblioteca.app;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BibliotecaService service = new BibliotecaService();

        boolean salir = false;
        while (!salir) {
            System.out.println("1) Agregar libro");
            System.out.println("2) Listar libros");
            System.out.println("3) Actualizar libro");
            System.out.println("4) Eliminar libro");
            System.out.println("5) Crear préstamo");
            System.out.println("6) Listar préstamos");
            System.out.println("7) Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> service.agregarLibro(scanner);
                case 2 -> service.listarLibros();
                case 3 -> service.actualizarLibro(scanner);
                case 4 -> service.eliminarLibro(scanner);
                case 5 -> service.crearPrestamo(scanner);
                case 6 -> service.listarPrestamos();
                case 7 -> salir = true;
                default -> System.out.println("Opción inválida.");
            }
        }

        System.out.println("Gracias, vuelva prontos.");
    }
}

# Sistema CRUD de Biblioteca en Java

Este proyecto fue desarrollado como parte de la preentrega del curso de Java. El sistema permite gestionar libros y préstamos utilizando programación orientada a objetos, colecciones y excepciones personalizadas.

## Funcionalidades

- Agregar, listar, actualizar y eliminar libros
- Crear préstamos con múltiples libros
- Validación de stock y cantidad al prestar
- Confirmación de operaciones sensibles
- Manejo de excepciones personalizadas
- Menú interactivo en consola

## Estructura del proyecto

- `com.biblioteca.libros`: clase `Libro`
- `com.biblioteca.prestamos`: clases `Prestamo` y `LineaPrestamo`
- `com.biblioteca.excepciones`: excepciones como `StockInsuficienteException`, `LibroNoEncontradoException`, etc.
- `com.biblioteca.app`: clase `BibliotecaService` y `Main`

## Tecnologías utilizadas

- Java 17+
- IntelliJ IDEA
- Git y GitHub

## Cómo ejecutar

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/Tashz/CRUDBibliotecaJavaTTech.git

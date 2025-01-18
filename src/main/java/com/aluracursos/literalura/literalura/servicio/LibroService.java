package com.aluracursos.literalura.literalura.servicio;

import com.aluracursos.literalura.literalura.dto.LibroDTO.LibroDTO;
import com.aluracursos.literalura.literalura.dto.LibroDTO.LibroDTO;
import com.aluracursos.literalura.literalura.entidad.Libro;
import com.aluracursos.literalura.literalura.repositorio.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

@Service
public class LibroService {

    @Autowired
    private LibroRepositorio libroRepositorio;

    @Autowired
    private HttpClientService httpClientService;

    public void buscarLibroPorTitulo(String titulo) {
        if (libroRepositorio.existsByTitulo(titulo)) {
            System.out.println("El libro ya existe en la base de datos.");
            return;
        }

        JsonNode libroJson = httpClientService.buscarLibroPorTitulo(titulo);
        if (libroJson != null) {
            String autor = libroJson.get("authors").get(0).get("name").asText();
            List<String> idiomas = libroJson.get("languages").findValuesAsText("");
            int descargas = libroJson.get("download_count").asInt();

            Libro libro = new Libro(titulo, idiomas, autor, descargas);
            libroRepositorio.save(libro);
            System.out.println("Libro guardado con éxito.");
        } else {
            System.out.println("No se encontró el libro en la API o ocurrió un error.");
        }
    }


    public void listarLibrosRegistrados() {
        List<Libro> libros = libroRepositorio.findAll();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados en la base de datos.");
        } else {
            List<com.aluracursos.literalura.literalura.dto.LibroDTO.LibroDTO> librosDTO = libros.stream()
                    .map(libro -> new LibroDTO(
                            libro.getTitulo(),
                            libro.getAutor(),
                            libro.getDescargas(),
                            new ArrayList<>(libro.getIdiomas()) // Copiar idiomas para evitar problemas
                    ))
                    .toList();

            librosDTO.forEach(libro -> System.out.println("Título: " + libro.getTitulo() +
                    ", Autor: " + libro.getAutor() +
                    ", Descargas: " + libro.getDescargas() +
                    ", Idiomas: " + libro.getIdiomas()));
        }
    }






    public void listarAutoresRegistrados() {
        libroRepositorio.findAll().stream()
                .map(Libro::getAutor)
                .distinct()
                .forEach(System.out::println);
    }

    public void listarAutoresVivosEnAnio(int anio) {
        // Este método se puede implementar si se conecta con el repositorio de Autor
        System.out.println("Funcionalidad en construcción.");
    }

    public void listarLibrosPorIdioma(String idioma) {
        List<Libro> libros = libroRepositorio.findByIdiomasContains(idioma);
        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros en el idioma especificado.");
        } else {
            libros.forEach(libro -> System.out.println("Título: " + libro.getTitulo() +
                    ", Autor: " + libro.getAutor()));
        }
    }
}

package com.aluracursos.literalura.literalura.dto.LibroDTO;

import java.util.List;

public class LibroDTO {
    private String titulo;
    private String autor;
    private int descargas;
    private List<String> idiomas;

    // Constructor
    public LibroDTO(String titulo, String autor, int descargas, List<String> idiomas) {
        this.titulo = titulo;
        this.autor = autor;
        this.descargas = descargas;
        this.idiomas = idiomas;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setDescargas(int descargas) {
        this.descargas = descargas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    // Getters
    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getDescargas() {
        return descargas;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }
}

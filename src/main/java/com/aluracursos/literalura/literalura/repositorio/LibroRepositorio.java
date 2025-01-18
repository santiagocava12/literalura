package com.aluracursos.literalura.literalura.repositorio;

import com.aluracursos.literalura.literalura.entidad.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, Long> {
    List<Libro> findByIdiomasContains(String idioma);
    boolean existsByTitulo(String titulo);

}

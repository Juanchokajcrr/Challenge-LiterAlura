package com.aluracursos.Challenge.LiterAlura.repository;

import com.aluracursos.Challenge.LiterAlura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor,Long> {

    @Query("SELECT a FROM Autor a WHERE a.nacimiento >= :anoBusqueda ORDER BY a.nacimiento ASC ")
    List<Autor> autorPorFecha(int anoBusqueda);

}

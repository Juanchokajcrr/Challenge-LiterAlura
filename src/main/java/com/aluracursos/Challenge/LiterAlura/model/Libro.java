package com.aluracursos.Challenge.LiterAlura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(unique = true)
    private String titulo;
    private String autor;
    private String idiomas;
    private Integer descargas;
    @ManyToOne
    private Libro libro;

    public Libro(){}
    public Libro(DatosResultados datosLibro) {
        this.titulo = datosLibro.titulo();
        this.autor = datosLibro.autores().get(0).nombre();
        this.idiomas = datosLibro.idiomas().get(0);
        this.descargas = datosLibro.descargas();
    }

    @Override
    public String toString() {
        return
                "Id=" + Id +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", idiomas='" + idiomas + '\'' +
                ", descargas=" + descargas;
    }

    public long getId() {
        return Id;
    }
    public void setId(long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIdiomas() {
        return idiomas;
    }
    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public Integer getDescargas() {
        return descargas;
    }
    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }

    public Libro getLibro() {
        return libro;
    }
    public void setLibro(Libro libro) {
        this.libro = libro;
    }
}

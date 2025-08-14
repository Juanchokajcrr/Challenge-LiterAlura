package com.aluracursos.Challenge.LiterAlura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")

public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @Column(unique = true)
    private String nombre;
    private Integer nacimiento;
    private Integer muerte;
//    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OneToMany
    private List<Libro> libros;

    public Autor (){}
    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        this.nacimiento = datosAutor.nacimiento();
        this.muerte = datosAutor.muerte();
    }

    @Override
    public String toString() {
        return
                "Id=" + Id +
                ", nombre='" + nombre + '\'' +
                ", añoNacimiento=" + nacimiento +
                ", añoMuerte=" + muerte;
    }

    public long getId() {
        return Id;
    }
    public void setId(long id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNacimiento() {
        return nacimiento;
    }
    public void setNacimiento(Integer añoNacimiento) {
        this.nacimiento = añoNacimiento;
    }

    public Integer getMuerte() {
        return muerte;
    }
    public void setMuerte(Integer añoMuerte) {
        this.muerte = añoMuerte;
    }

//    public List<Libro> getLibros() {
//        return libros;
//    }
//    public void setLibros(List<Libro> libros) {
//        this.libros = libros;
//    }
}

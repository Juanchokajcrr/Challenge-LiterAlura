package com.aluracursos.Challenge.LiterAlura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "autores")

public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @Column(unique = true)
    private String nombre;
    private Integer Nacimiento;
    private Integer Muerte;

    public Autor (){}
    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        this.Nacimiento = datosAutor.Nacimiento();
        this.Muerte = datosAutor.Muerte();
    }

    @Override
    public String toString() {
        return
                "Id=" + Id +
                ", nombre='" + nombre + '\'' +
                ", añoNacimiento=" + Nacimiento +
                ", añoMuerte=" + Muerte;
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

    public Integer getAñoNacimiento() {
        return Nacimiento;
    }
    public void setAñoNacimiento(Integer añoNacimiento) {
        this.Nacimiento = añoNacimiento;
    }

    public Integer getAñoMuerte() {
        return Muerte;
    }
    public void setAñoMuerte(Integer añoMuerte) {
        this.Muerte = añoMuerte;
    }
}

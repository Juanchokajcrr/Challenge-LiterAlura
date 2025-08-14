package com.aluracursos.Challenge.LiterAlura.principal;

import com.aluracursos.Challenge.LiterAlura.model.Autor;
import com.aluracursos.Challenge.LiterAlura.model.DatosLibro;
import com.aluracursos.Challenge.LiterAlura.model.Libro;
import com.aluracursos.Challenge.LiterAlura.repository.AutorRepository;
import com.aluracursos.Challenge.LiterAlura.repository.LibroRepository;
import com.aluracursos.Challenge.LiterAlura.service.ConsumoAPI;
import com.aluracursos.Challenge.LiterAlura.service.ConvierteDatos;

import java.util.List;
import java.util.Scanner;

public class Main {

    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private Scanner teclado = new Scanner(System.in);
    private final String URL_BASE = "http://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;
    private List<Libro> libros;
    private List<Autor> autores;

    public Main(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    *****************************************************************************\n
                    Elija una opción del menú, seleccionado el numero correspondiente:
                    \n
                    1 - Buscar libro por titulo y/o autor
                    2 - Listar libros registrados
                    3 - listar autores registrados
                    4 - listar autores vivos en un determinado año
                    5 - listar libros por idioma
                    \n
                    0 - Salir
                    \n********************************************************************************
                    """;
            try {
                System.out.println(menu);
                opcion = teclado.nextInt();
                teclado.nextLine();
            }catch (Exception e){
                System.out.println("Por favor, ingrese un número válido.");
                teclado.nextLine();
                continue;
            }


            switch (opcion) {
                case 1:
                    buscarLibro();
                    break;
//                case 2:
//                    buscarEpisodioPorSerie();
//                    break;
//                case 3:
//                    mostrarSeriesBuscadas();
//                    break;
//                case 4:
//                    buscarSeriesPorTitulo();
//                    break;
//                case 5:
//                    buscarTop5Series();
//                    break;

                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    //Extrae los datos del libro desde la API
    private DatosLibro getDatosLibro() {
        System.out.println("Escribe el nombre del libro que deseas buscar");
        var busqueda = teclado.nextLine().toLowerCase().replace(" ", "%20");
        var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + busqueda);
        //System.out.println(json);
        DatosLibro datos = conversor.obtenerDatos(json, DatosLibro.class);
        return datos;
    }

    //caso 1 del menu buscar libro y guardar en la DB tablas correspondientes
    private void buscarLibro() {

        DatosLibro datosLibro = getDatosLibro();

        try {
            Libro libro = new Libro(datosLibro.resultados().get(0));
            Autor autor = new Autor(datosLibro.resultados().get(0).autores().get(0));

            System.out.println("""
                    libro[
                        titulo: %s
                        author: %s
                        lenguaje: %s
                        descargas: %s
                    ]
                    """.formatted(
                            libro.getTitulo(),
                            autor.getNombre(),
                            libro.getIdiomas(),
                            libro.getDescargas().toString()));

            libroRepository.save(libro);
            autorRepository.save(autor);

        }catch (Exception e) {
            System.out.println("no se encontro ese libro");
        }
    }
}

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
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutorPorAno();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;

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
                        autor: %s
                        idiomas: %s
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

    // caso 2 listar libros registrados
    private void listarLibrosRegistrados() {
        libros = libroRepository.findAll();
        libros.stream().forEach(l -> {
            System.out.println("""
                    libro[
                        titulo: %s
                        autor: %s
                        idiomas: %s
                        descargas: %s
                    ]
                    """.formatted(
                    l.getTitulo(),
                    l.getAutor(),
                    l.getIdiomas(),
                    l.getDescargas().toString()));
        });
    }

    // caso 3 listar autores registrados
    private void listarAutoresRegistrados(){
        autores = autorRepository.findAll();
        autores.stream().forEach(a -> {
            System.out.println("""
                    autor[
                        nombre: %s
                        naciminto: %s
                        muerte: %s
                    ]
                    """.formatted(
                            a.getNombre(),
                    a.getNacimiento(),
                    a.getMuerte()));
        });
    }

    // caso 4 listar autores vivos en un determinado año
    private void listarAutorPorAno() {
        System.out.println("Escribe el año que deseas consultar");
        var anoBusqueda = teclado.nextInt();
        teclado.nextLine();

        List<Autor> autores = autorRepository.autorPorFecha(anoBusqueda);
        autores.forEach(a -> {
            System.out.println("""
                    autor[
                        nombre: %s
                        naciminto: %s
                        muerte: %s
                    ]
                    """.formatted(
                    a.getNombre(),
                    a.getNacimiento().toString(),
                    a.getMuerte().toString()));
        });
    }

    // caso 5 listar libros por idioma
    private void  listarLibrosPorIdioma() {
        System.out.println("""
                \n****************************************************************
                    Selcciona el lenguaje de los libros que deseas consultar
                ****************************************************************\n
                1 - En (Ingles)
                2 - Es (Español)
                \n""");

        try {
            var opcion2 = teclado.nextInt();
            teclado.nextLine();

            switch (opcion2){
                case 1:
                    libros = libroRepository.findByIdioma("en");
                    break;
                case 2:
                    libros = libroRepository.findByIdioma("es");
                    break;
                default:
                    System.out.println("Opción inválida, por favor ingrese una opcion disponible");
            }

            libros.stream().forEach(l -> {
                    System.out.println("""
                    autor[
                        titulo: %s
                        autor: %s
                        idiomas: %s
                        descargas: %s
                    ]
                    """.formatted(
                            l.getTitulo(),
                            l.getAutor(),
                            l.getIdiomas(),
                            l.getDescargas().toString()));

            });

        }catch (Exception e) {
            System.out.println("Por favor, ingrese un número válido.");
        }
    }
}

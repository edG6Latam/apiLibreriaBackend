package com.prueba.api.principal;

import com.prueba.api.model.*;
import com.prueba.api.repository.AutorRepository;
import com.prueba.api.repository.LibroRepository;
import com.prueba.api.service.ConvertirDatos;
import com.prueba.api.service.ObtenerDatos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    ObtenerDatos obtenerDatos= new ObtenerDatos();
    ConvertirDatos convertirDatos= new ConvertirDatos();
    String URL_API="https://gutendex.com/books/";
    Scanner scan= new Scanner(System.in);
    private LibroRepository libRepositorio;
    private AutorRepository autRepositorio;


    public Principal(LibroRepository libroRepository,AutorRepository autorRepository) {
       this.libRepositorio=libroRepository;
       this.autRepositorio=autorRepository;

    }

    public void principal(){
        System.out.println("****** Api de Libros ********");
        Integer contador=0;
        while (contador!=5){
            System.out.println("""
                    *****************************
                    1- Ver todos los libros
                    2- buscar por nombre
                    3- buscar por fecha
                    4- top de los 5 mas descargados
                    6- buscar nacidos en 1980
                    5- Salir
                    *****************************
                    """);
            Integer opcion= scan.nextInt();
            switch (opcion){
                case 1:
                    todosLosLibros();
                    break;
                case 2:
                    buscarPorNombre();
                    break;
                case 3:
                    buscarPorFecha();
                    break;
                case 4:
                    masDescargadosLibros();
                    break;
                case 6:
                    descargasMayores40000();
                    break;
                case 5:
                    System.out.println("saliste del programa");
                    contador=5;
                    break;
                default:
                    System.out.println("opcion no valida");
                    break;

            }

        }


    }

    private void descargasMayores40000() {
       List<Libro> nacidos1980= libRepositorio.descargasMayores40000();
        System.out.println(nacidos1980);
    }

    private void buscarPorFecha() {
        System.out.println("ingrese la fecha de nacimiento");
        var born=scan.nextInt();
        System.out.println("ingrese fecha de muerte");
        var death= scan.nextInt();
        scan.nextLine();
            var json=obtenerDatos.traerDatos(URL_API+"?author_year_start="+born+"&author_year_end="+death);
            getDatosApi(json);

    }

    private void buscarPorNombre() {
        System.out.println("ingrese el nombre de el libro");
        var busqueda=scan.next();
        scan.nextLine();
        if(busqueda!=null){
            var json=obtenerDatos.traerDatos(URL_API+"?search="+busqueda);
            getDatosApi(json);

        }else{
            System.out.println("opcion no valida");
        }

    }

    private void masDescargadosLibros() {
        var json=obtenerDatos.traerDatos(URL_API);
        var datos=convertirDatos.conversor(json, RecordResults.class);
        datos.libro().stream()
                .sorted(Comparator.comparing(RecordLibros::descargas).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }

    private void todosLosLibros() {
        var json=obtenerDatos.traerDatos(URL_API);
        getDatosApi(json);
    }
    private void getDatosApi(String json){
        var datos=convertirDatos.conversor(json, RecordResults.class);
       List<RecordLibros> listRlibro= datos.libro();
       List<Libro> listLibro=new ArrayList<>();
        for (RecordLibros rLibros:
             listRlibro) {
            Autor autor=new Autor(rLibros.autores().get(0));
            autRepositorio.save(autor);
            Libro libro= new Libro(rLibros,autor);
            libRepositorio.save(libro);
            listLibro.add(libro);
            System.out.println(libro);
        }

    }
}

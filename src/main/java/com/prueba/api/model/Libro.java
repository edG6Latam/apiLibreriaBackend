package com.prueba.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long identidad;
    private Integer id;
    @Column(unique = true)
    private String titulo;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_autor")
    private Autor autor;
    private String idiomas;
    private Integer descargas;


    public Libro(RecordLibros libros,Autor autor) {
        this.id= libros.id();
        this.titulo= libros.titulo();
        this.idiomas= libros.idiomas().toString();
        this.descargas= libros.descargas();
        this.autor =autor;

    }



    @Override
    public String toString() {
        return "******************"+'\n' +
                "id_libro=" + identidad +'\n' +
                "id=" + id +'\n' +
                "titulo='" + titulo + '\n' +
                "autor=" + autor +'\n' +
                "idiomas=" + idiomas +'\n' +
                "descargas=" + descargas+'\n'+
        "******************"+'\n';
    }

}

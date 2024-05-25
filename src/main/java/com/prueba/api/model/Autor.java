package com.prueba.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "autores")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer fechaNacimiento;
    private Integer fechaFallecimiento;
   @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "autor")
    List<Libro> libros;

    public Autor(RecordAutor recordAutor) {
        this.nombre= recordAutor.nombre();
        this.fechaFallecimiento= recordAutor.fechaFallecimiento();
        this.fechaNacimiento= recordAutor.fechaNacimiento();
    }


    @Override
    public String toString() {
        return "%%%%%%%%%%%%%%" +'\n' +
                "nombre='" + nombre + '\n' +
                "fechaNacimiento=" + fechaNacimiento +'\n' +
                "fechaFallecimiento=" + fechaFallecimiento +'\n' +
                "%%%%%%%%%%%%%%";
    }
}

package com.prueba.api.repository;

import com.prueba.api.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro,Long> {

    @Query(value = "select * from libros where descargas>=40000;",nativeQuery = true)
    List<Libro> descargasMayores40000();

}

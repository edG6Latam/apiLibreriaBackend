package com.prueba.api.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RecordAutor(
        @JsonAlias("name")
        String nombre,
        @JsonAlias("birth_year")
        Integer fechaNacimiento,
        @JsonAlias("death_year")
        Integer fechaFallecimiento
) {

}

package com.prueba.api.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record RecordResults(
        @JsonAlias("results")
        List<RecordLibros> libro
) {
}

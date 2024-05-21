package com.jonasSmendes.livraria.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LivroData(
        @JsonProperty("results") List<Result> resultados
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Result(
            @JsonProperty("id") Long id,
            @JsonProperty("title") String tituloLivro,
            @JsonProperty("authors") List<Author> autores,
            @JsonProperty("languages") List<String> linguagem,
            @JsonProperty("download_count") int numeroDeDownload
    ) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Author(
            @JsonProperty("name") String nomeAutor,
            @JsonProperty("birth_year") int dataDeAniversario,
            @JsonProperty("death_year") int dataDefalecimento
    ) {}


    @Override
    public String toString() {
        return "resultados=" + resultados +
                '}';
    }
}

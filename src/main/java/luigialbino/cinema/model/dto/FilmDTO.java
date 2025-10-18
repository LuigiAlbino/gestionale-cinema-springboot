package luigialbino.cinema.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmDTO {
    @Schema(description = "Identificativo univoco del film", example = "1")
    private Long id;

    @Schema(description = "Titolo del film", example = "Inception")
    private String titolo;

    @Schema(description = "Durata del film in minuti", example = "148")
    private Integer durata;

    @Schema(description = "Descrizione o trama del film", example = "Un ladro che ruba segreti attraverso i sogni.")
    private String descrizione;

    @Schema(description = "Anno di uscita del film", example = "2009")
    private Integer dataUscita;

    @Schema(description = "Nome del regista", example = "Christopher Nolan")
    private String regista;
}

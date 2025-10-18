package luigialbino.cinema.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProiezioneDTO {
    @Schema(description = "Identificativo univoco della proiezione", example = "1")
    private Long id;

    @Schema(description = "Film proiettato", implementation = FilmDTO.class)
    private FilmDTO film;

    @Schema(description = "Sala in cui avviene la proiezione", implementation = SalaDTO.class)
    private SalaDTO sala;

    @Schema(description = "Data e ora di inizio della proiezione", example = "2025-10-10T21:00:00")
    private LocalDateTime dataOraInizio;

    @Schema(description = "Prezzo del biglietto per la proiezione", example = "9.50")
    private double prezzoBiglietto;
}

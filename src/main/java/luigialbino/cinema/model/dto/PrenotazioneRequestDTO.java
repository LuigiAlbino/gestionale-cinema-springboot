package luigialbino.cinema.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrenotazioneRequestDTO {
    @Schema(description = "Identificativo univoco della proiezione", example = "1")
    private Long idProiezione;

    @Schema(description = "Numero di posti prenotati", example = "3")
    private int postiPrenotati;
}

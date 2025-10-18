package luigialbino.cinema.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrenotazioneDTO {
    @Schema(description = "Identificativo univoco della prenotazione", example = "1")
    private Long id;

    @Schema(description = "Utente che ha eseguito la prenotazione", implementation = UtenteDTO.class)
    private UtenteDTO utente;

    @Schema(description = "Proiezione legata alla prenotazione", implementation = ProiezioneDTO.class)
    private ProiezioneDTO proiezione;

    @Schema(description = "Numero di posti prenotati", example = "3")
    private int postiPrenotati;

    @Schema(description = "???", example = "1")
    private String stato;
}

package luigialbino.cinema.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PrenotazioneResponseDTO {
    private String nomeFilm;
    private String nomeSala;
    private Integer numPosti;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataOraPrenotazione;
}

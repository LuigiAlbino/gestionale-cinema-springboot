package luigialbino.cinema.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ErrorResponseDTO {
    @Schema(description = "Codice errore", example = "404")
    private int status;

    @Schema(description = "Messaggio errore", example = "Film non trovato")
    private String messaggio;

    @Schema(description = "Timestamp dell'errore", example = "2025-11-03T17:33:06.290154")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;

    public ErrorResponseDTO(int status, String messaggio) {
        this.status = status;
        this.messaggio = messaggio;
        this.timestamp = LocalDateTime.now();
    }
}

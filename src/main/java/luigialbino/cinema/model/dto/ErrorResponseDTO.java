package luigialbino.cinema.model.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ErrorResponseDTO {
    private int status;
    private String messaggio;
    private LocalDateTime timestamp;

    public ErrorResponseDTO(int status, String messaggio) {
        this.status = status;
        this.messaggio = messaggio;
        this.timestamp = LocalDateTime.now();
    }
}

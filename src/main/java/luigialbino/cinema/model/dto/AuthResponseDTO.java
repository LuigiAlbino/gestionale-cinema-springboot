package luigialbino.cinema.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthResponseDTO {
    @Schema(description = "Token di risposta al login")
    private String token;
    private String tokenType = "Bearer";

    public AuthResponseDTO(String token) {
        this.token = token;
    }
}

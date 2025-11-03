package luigialbino.cinema.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class ModificaPasswordDTO {
    @Schema(description = "Email dell'utente", example = "mario.rossi@email.com", required = true)
    private String email;

    @Schema(description = "Vecchia password dell'utente", example = "PasswordVecchia123!", required = true)
    private String vecchiaPassword;

    @Schema(description = "Nuova password dell'utente", example = "NuovaPassword456!", required = true)
    private String nuovaPassword;
}

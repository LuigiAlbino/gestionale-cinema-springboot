package luigialbino.cinema.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtenteDTO {

    @Schema(description = "Identificativo univoco dell'utente", example = "1")
    private Long id;

    @Schema(description = "Nome utente", example = "Mario")
    private String nome;

    @Schema(description = "Cognome utente", example = "Rossi")
    private String cognome;

    @Schema(description = "Email di registrazione", example = "mario.rossi@email.com")
    private String email;

    @Schema(description = "Ruolo svolto nel sistema. ADMIN/USER/FIRSTACCESS", example = "ADMIN")
    private String ruolo;

    @Schema(description = "Recapito telefonico", example = "3339988777")
    private String recapitoTelefonico;
}

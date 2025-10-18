package luigialbino.cinema.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterRequestDTO {
    @Schema(description = "Nome utente", example = "Mario")
    private String nome;

    @Schema(description = "Cognome utente", example = "Rossi")
    private String cognome;

    @Schema(description = "Recapito telefonico", example = "3339988777")
    private String recapitoTelefonico;

    @Schema(description = "Email di registrazione", example = "mario.rossi@email.com")
    private String email;

    @Schema(description = "Password di registrazione", example = "blablabla1")
    private String password;

    public RegisterRequestDTO(String nome, String cognome, String recapitoTelefonico, String email, String password, String ruolo) {
        this.nome = nome;
        this.cognome = cognome;
        this.recapitoTelefonico = recapitoTelefonico;
        this.email = email;
        this.password = password;
    }
}

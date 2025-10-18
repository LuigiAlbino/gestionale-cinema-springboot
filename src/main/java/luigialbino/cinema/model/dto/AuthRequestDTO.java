package luigialbino.cinema.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequestDTO {
    @Schema(description = "Email di registrazione", example = "mario.rossi@email.com")
    private String email;
    @Schema(description = "Password utilizzata per la registrazione", example = "blablabla")
    private String password;
}

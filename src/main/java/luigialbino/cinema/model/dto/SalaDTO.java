package luigialbino.cinema.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaDTO {
    @Schema(description = "Identificativo univoco della sala", example = "1")
    private Long id;

    @Schema(description = "Nome della sala", example = "Sala1")
    private String nome;

    @Schema(description = "Numero di posti presenti nella sala", example = "200")
    private int numeroPosti;
}

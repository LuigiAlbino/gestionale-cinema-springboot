package luigialbino.cinema.model.dto;

import lombok.Data;

@Data
public class ModificaPasswordDTO {
    private String email;
    private String vecchiaPassword;
    private String nuovaPassword;
}

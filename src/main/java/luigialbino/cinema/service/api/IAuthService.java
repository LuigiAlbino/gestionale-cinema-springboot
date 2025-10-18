package luigialbino.cinema.service.api;

import luigialbino.cinema.model.dto.AuthRequestDTO;
import luigialbino.cinema.model.dto.AuthResponseDTO;
import luigialbino.cinema.model.dto.RegisterRequestDTO;

public interface IAuthService {
    void registrazione(RegisterRequestDTO request);

    AuthResponseDTO login(AuthRequestDTO request);
}

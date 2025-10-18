package luigialbino.cinema.service.api;

import luigialbino.cinema.model.dto.ModificaPasswordDTO;
import luigialbino.cinema.model.dto.PageResponseDTO;
import luigialbino.cinema.model.dto.UtenteDTO;
import org.springframework.data.domain.Pageable;

public interface IUtenteService {

    PageResponseDTO<UtenteDTO> getAll(Pageable pageable);

    UtenteDTO getUtenteById(Long id);

    UtenteDTO createUtente(UtenteDTO utenteDTO);

    UtenteDTO aggiornaUtente(Long id, UtenteDTO utenteDTO);

    void deleteUtente(Long id);

    void modificaPasswordPrimoAccesso(ModificaPasswordDTO request);
}

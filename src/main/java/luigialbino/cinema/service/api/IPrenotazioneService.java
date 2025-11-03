package luigialbino.cinema.service.api;

import luigialbino.cinema.model.dto.PageResponseDTO;
import luigialbino.cinema.model.dto.PrenotazioneRequestDTO;
import luigialbino.cinema.model.dto.PrenotazioneResponseDTO;
import org.springframework.data.domain.Pageable;

public interface IPrenotazioneService {

    PageResponseDTO<PrenotazioneResponseDTO> leMiePrenotazioni(Pageable pageable);

    PrenotazioneResponseDTO creaPrenotazione(PrenotazioneRequestDTO dto);
}
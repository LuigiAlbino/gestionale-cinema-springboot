package luigialbino.cinema.controller;

import luigialbino.cinema.model.dto.PageResponseDTO;
import luigialbino.cinema.model.dto.PrenotazioneResponseDTO;
import luigialbino.cinema.service.impl.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/prenotazione")
public class PrenotazioneController {
    @Autowired
    private PrenotazioneService prenotazioneService;

    @GetMapping("/mie-prenotazioni")
    public ResponseEntity<PageResponseDTO<PrenotazioneResponseDTO>> leMiePrenotazioni(@PageableDefault(sort = "proiezione.dataOraInizio") Pageable pageable){
        PageResponseDTO<PrenotazioneResponseDTO> response = prenotazioneService.leMiePrenotazioni(pageable);
        return ResponseEntity.ok(response);
    }
}

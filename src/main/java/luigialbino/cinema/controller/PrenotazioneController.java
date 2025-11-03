package luigialbino.cinema.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import luigialbino.cinema.model.dto.PageResponseDTO;
import luigialbino.cinema.model.dto.PrenotazioneRequestDTO;
import luigialbino.cinema.model.dto.PrenotazioneResponseDTO;
import luigialbino.cinema.service.impl.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prenotazione")
public class PrenotazioneController {
    @Autowired
    private PrenotazioneService prenotazioneService;

    @GetMapping("/mie-prenotazioni")
    @Operation(summary = "Elenca tutte le prenotazionin eseguite dall'utente loggato")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Prenotazioni recuperate con successo"),
            @ApiResponse(responseCode = "404", description = "Nessun prenotazione disponibile")
    })
    public ResponseEntity<PageResponseDTO<PrenotazioneResponseDTO>> leMiePrenotazioni(@PageableDefault(sort = "proiezione.dataOraInizio") Pageable pageable){
        PageResponseDTO<PrenotazioneResponseDTO> response = prenotazioneService.leMiePrenotazioni(pageable);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/crea-prenotazione")
    @Operation(summary = "Permette inserimento di una prenotazione")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Prenotazione inserito con successo"),
            @ApiResponse(responseCode = "404", description = "Nessuna proiezione con id indicato")
    })
    public ResponseEntity<PrenotazioneResponseDTO> creaPrenotazione(@RequestBody PrenotazioneRequestDTO dto){
        return ResponseEntity.ok(prenotazioneService.creaPrenotazione(dto));
    }
}

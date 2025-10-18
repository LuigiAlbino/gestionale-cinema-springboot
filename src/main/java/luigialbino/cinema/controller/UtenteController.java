package luigialbino.cinema.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import luigialbino.cinema.model.dto.PageResponseDTO;
import luigialbino.cinema.model.dto.UtenteDTO;
import luigialbino.cinema.service.impl.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/utenti")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping("/tutti-utenti")
    @Operation(summary = "Elenca tutti gli utenti")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utenti trovati con successo"),
            @ApiResponse(responseCode = "404", description = "Nessun utente trovato")
    })
    public ResponseEntity<PageResponseDTO<UtenteDTO>> getAll(@PageableDefault(sort = "id") Pageable pageable){
        PageResponseDTO<UtenteDTO> response = utenteService.getAll(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utente trovato con successo"),
            @ApiResponse(responseCode = "404", description = "Utente non trovato")
    })
    @Operation(summary = "Cerca utente per id")
    public ResponseEntity<UtenteDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(utenteService.getUtenteById(id));
    }

    @PostMapping("/inserisci-utente")
    @Operation(summary = "Crea nuovo utente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utente inserito con successo"),
            @ApiResponse(responseCode = "409", description = "Utente già presente")
    })
    public ResponseEntity<UtenteDTO> createFilm(@Parameter(description = "Dati dell'utente da creare")
                                              @RequestBody UtenteDTO utenteDTO){
        return ResponseEntity.ok(utenteService.createUtente(utenteDTO));
    }

    @PatchMapping("/modifica-utente/{id}")
    @Operation(summary = "Modifica utente esistente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utente aggiornato con successo"),
            @ApiResponse(responseCode = "404", description = "Utente non trovato")
    })
    public ResponseEntity<UtenteDTO> aggiornaFilm(@Parameter(description = "ID dell'utente da aggiornare", example = "1")
                                                @PathVariable Long id,
                                                @Parameter(description = "Nuovi dati dell'utente")
                                                @RequestBody UtenteDTO utenteDTO){
        return ResponseEntity.ok(utenteService.aggiornaUtente(id, utenteDTO));
    }

    @DeleteMapping("/elimina-utente/{id}")
    @Operation(summary = "Elimina un utente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Utente eliminato con successo"),
            @ApiResponse(responseCode = "404", description = "Utente non trovato")
    })
    public ResponseEntity<Void> deleteFilm(@Parameter(description = "ID dell'utente da eliminare", example = "1")
                                           @PathVariable Long id){
        utenteService.deleteUtente(id);
        return ResponseEntity.noContent().build();
    }
}

package luigialbino.cinema.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import luigialbino.cinema.model.dto.PageResponseDTO;
import luigialbino.cinema.model.dto.FilmDTO;
import luigialbino.cinema.service.impl.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/film")
public class FilmController {

    @Autowired
    private FilmService filmService;

    @GetMapping("/tutti-film")
    @Operation(summary = "Elenca tutti i film")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Film trovati con successo"),
            @ApiResponse(responseCode = "404", description = "Nessun film trovato")
    })
    public ResponseEntity<PageResponseDTO<FilmDTO>> getAll3(@PageableDefault(sort = "dataUscita") Pageable pageable){
        PageResponseDTO<FilmDTO> response = filmService.getAll(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Film trovato con successo"),
            @ApiResponse(responseCode = "404", description = "Film non trovato")
    })
    @Operation(summary = "Cerca film per id")
    public ResponseEntity<FilmDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(filmService.getById(id));
    }

    @PostMapping("/inserisci-film")
    @Operation(summary = "Crea nuovo film")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Film inserito con successo"),
            @ApiResponse(responseCode = "409", description = "Film già presente a catalogo")
    })
    public ResponseEntity<FilmDTO> createFilm(@Parameter(description = "Dati del film da creare")
                                              @RequestBody FilmDTO filmDTO){
        return ResponseEntity.ok(filmService.createFilm(filmDTO));
    }

    @PatchMapping("/modifica-film/{id}")
    @Operation(summary = "Modifica film esistente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Film aggiornato con successo"),
            @ApiResponse(responseCode = "404", description = "Film non trovato")
    })
    public ResponseEntity<FilmDTO> aggiornaFilm(@Parameter(description = "ID del film da aggiornare", example = "1")
                                                @PathVariable Long id,
                                                @Parameter(description = "Nuovi dati del film")
                                                @RequestBody FilmDTO filmDTO){
        return ResponseEntity.ok(filmService.aggiornaFilm(id, filmDTO));
    }

    @DeleteMapping("/elimina-film/{id}")
    @Operation(summary = "Elimina un film")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Film eliminato con successo"),
            @ApiResponse(responseCode = "404", description = "Film non trovato")
    })
    public ResponseEntity<Void> deleteFilm(@Parameter(description = "ID del film da eliminare", example = "1")
                                           @PathVariable Long id){
        filmService.deleteFilm(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cerca-film-per-regista-anno")
    @Operation(summary = "Cerca film per regista, è possibile inserire un range temporale")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Film trovati con successo"),
            @ApiResponse(responseCode = "404", description = "Nessun film trovato"),
            @ApiResponse(responseCode = "409", description = "Anno Inizio maggiore di Anno Fine")
    })
    public ResponseEntity<PageResponseDTO<FilmDTO>> cercaPerRegistaEAnno(
            @Parameter(description = "Nome del regista", example = "Christopher Nolan")
            @RequestParam String regista,
            @Parameter(description = "Anno iniziale del range (opzionale)", example = "2000")
            @RequestParam(required = false) Integer annoInizio,
            @Parameter(description = "Anno finale del range (opzionale)", example = "2025")
            @RequestParam(required = false) Integer annoFine,
            @PageableDefault(size = 10, sort = "id")Pageable pageable){
        return ResponseEntity.ok(filmService.cercaPerRegistaEAnno(regista, annoInizio, annoFine, pageable));
    }

    @GetMapping("/cerca-film-per-anno")
    @Operation(summary = "Cerca film con range temporale a scelta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Film trovati con successo"),
            @ApiResponse(responseCode = "404", description = "Nessun film trovato"),
            @ApiResponse(responseCode = "409", description = "Anno Inizio maggiore di Anno Fine")
    })
    public ResponseEntity<PageResponseDTO<FilmDTO>> cercaFilmPerAnno(
            @Parameter(description = "Anno iniziale del range (opzionale)", example = "2010")
            @RequestParam(required = false) Integer annoInizio,
            @Parameter(description = "Anno finale del range (opzionale)", example = "2025")
            @RequestParam(required = false) Integer annoFine,
            @PageableDefault(size = 10, sort = "dataUscita")Pageable pageable){

        PageResponseDTO<FilmDTO> response = filmService.cercaFilmPerAnno(annoInizio, annoFine, pageable);
        return ResponseEntity.ok(response);
    }
}

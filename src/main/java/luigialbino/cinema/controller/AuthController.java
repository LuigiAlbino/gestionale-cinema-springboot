package luigialbino.cinema.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import luigialbino.cinema.model.dto.AuthRequestDTO;
import luigialbino.cinema.model.dto.AuthResponseDTO;
import luigialbino.cinema.model.dto.ModificaPasswordDTO;
import luigialbino.cinema.model.dto.RegisterRequestDTO;
import luigialbino.cinema.service.impl.AuthService;
import luigialbino.cinema.service.impl.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    UtenteService utenteService;

    @PostMapping("/login")
    @Operation(summary = "Endpoint per il login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login avvenuto con successo"),
            @ApiResponse(responseCode = "400", description = "Email o password non validi")
    })
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO request) {
        AuthResponseDTO response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/registrazione")
    @Operation(summary = "Endpoint per la registrazione")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utente registrato con successo"),
            @ApiResponse(responseCode = "409", description = "Utente già presente a sistema")
    })
    public ResponseEntity<Void> registrazione(@RequestBody RegisterRequestDTO request){
        authService.registrazione(request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/modifica-password-primo-accesso")
    public ResponseEntity<Void> modificaPassword(@RequestBody ModificaPasswordDTO request){
        utenteService.modificaPasswordPrimoAccesso(request);
        return ResponseEntity.noContent().build();
    }
}

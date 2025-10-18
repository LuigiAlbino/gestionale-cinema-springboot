package luigialbino.cinema.service.impl;

import luigialbino.cinema.exception.GestioneException;
import luigialbino.cinema.model.dto.AuthRequestDTO;
import luigialbino.cinema.model.dto.AuthResponseDTO;
import luigialbino.cinema.model.dto.RegisterRequestDTO;
import luigialbino.cinema.model.entity.Ruolo;
import luigialbino.cinema.model.entity.Utente;
import luigialbino.cinema.repository.UtenteRepository;
import luigialbino.cinema.security.CustomUserDetails;
import luigialbino.cinema.security.CustomUserDetailsService;
import luigialbino.cinema.security.JwtService;
import luigialbino.cinema.service.api.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtService jwtService;

    @Override
    public AuthResponseDTO login(AuthRequestDTO request){
        try{
            UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
            );
            authenticationManager.authenticate(user);

            CustomUserDetails userDetails = (CustomUserDetails)
                    customUserDetailsService.loadUserByUsername(request.getEmail());

            // Genera JWT
            String token = jwtService.generateToken(
                    userDetails.getUsername(),
                    userDetails.getAuthorities().stream()
                            .findFirst()
                            .map(a -> a.getAuthority())
                            .orElse("ROLE_USER")
            );

            return new AuthResponseDTO(token);
        } catch (AuthenticationException ex) {
            throw GestioneException.badRequest("Email o password non validi");
        }
    }

    @Override
    public void registrazione(RegisterRequestDTO request){
        boolean exists =  utenteRepository.existsByEmailIgnoreCase(request.getEmail());

        if(exists){
            Utente utente = utenteRepository.findByEmailIgnoreCase(request.getEmail());
            Long idUtente = utente.getId();
            throw GestioneException.conflict("Utente già presente con id: " + idUtente);
        } else{
            Utente utente = new Utente();
            utente.setNome(request.getNome());
            utente.setCognome(request.getCognome());
            utente.setRecapitoTelefonico(request.getRecapitoTelefonico());
            utente.setEmail(request.getEmail());
            utente.setPassword(passwordEncoder.encode(request.getPassword()));
            utente.setIsFirstAccess(false);
            utente.setRuolo(Ruolo.valueOf(Ruolo.ROLE_USER.toString()));

            utenteRepository.save(utente);
        }
    }
}

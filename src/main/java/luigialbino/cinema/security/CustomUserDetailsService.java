package luigialbino.cinema.security;

import luigialbino.cinema.model.entity.Utente;
import luigialbino.cinema.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UtenteRepository utenteRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utente utente = utenteRepository.findByEmailIgnoreCase(email);

        if (utente == null) {
            throw new UsernameNotFoundException("Utente non trovato con email: " + email);
        }

        return new CustomUserDetails(utente);
    }
}

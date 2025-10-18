package luigialbino.cinema.repository;

import luigialbino.cinema.model.entity.Film;
import luigialbino.cinema.model.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {

    boolean existsByEmailIgnoreCase(String email);

    Utente findByEmailIgnoreCase(String email);
}

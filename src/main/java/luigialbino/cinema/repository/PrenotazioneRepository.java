package luigialbino.cinema.repository;

import luigialbino.cinema.model.entity.Prenotazione;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    Page<Prenotazione> findByUtenteId(Long utenteId, Pageable pageable);

    @Query("SELECT SUM(p.postiPrenotati) FROM Prenotazione p WHERE p.proiezione.id = :proiezioneId")
    Integer sumPostiPrenotatiByProiezioneId(@Param("proiezioneId") Long proiezioneId);
}

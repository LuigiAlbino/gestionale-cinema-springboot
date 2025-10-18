package luigialbino.cinema.repository;

import luigialbino.cinema.model.entity.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

    boolean existsByTitoloIgnoreCase(String titolo);

    Film findByTitoloIgnoreCase(String titolo);

    @Query("SELECT f FROM Film f WHERE f.regista LIKE %:regista% " +
            "AND (:annoInizio IS NULL OR f.dataUscita >= :annoInizio) " +
            "AND (:annoFine IS NULL OR f.dataUscita <= :annoFine) " +
            "ORDER BY f.dataUscita DESC")
    Page<Film> cercaPerRegistaEAnno(@Param("regista") String regista,
                                    @Param("annoInizio") Integer annoInizio,
                                    @Param("annoFine") Integer annoFine,
                                    Pageable pageable);

    Page<Film> findByDataUscitaBetween(Integer annoInizio, Integer annoFine, Pageable pageable);

    @Query("SELECT MIN(f.dataUscita) FROM Film f")
    Integer findMinDataUscita();

    @Query("SELECT MAX(f.dataUscita) FROM Film f")
    Integer findMaxDataUscita();
}

package luigialbino.cinema.repository;

import luigialbino.cinema.model.entity.Proiezione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProiezioneRepository extends JpaRepository<Proiezione, Long> {
}

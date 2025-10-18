package luigialbino.cinema.service.api;

import luigialbino.cinema.model.dto.PageResponseDTO;
import luigialbino.cinema.model.dto.FilmDTO;
import org.springframework.data.domain.Pageable;

public interface IFilmService {

    PageResponseDTO<FilmDTO> getAll(Pageable pageable);

    FilmDTO getById(Long id);

    FilmDTO createFilm(FilmDTO filmDTO);

    FilmDTO aggiornaFilm(Long id, FilmDTO filmDTO);

    void deleteFilm(Long id);

    PageResponseDTO<FilmDTO> cercaPerRegistaEAnno(String regista, Integer annoInizio, Integer annoFine, Pageable pageable);

    PageResponseDTO<FilmDTO> cercaFilmPerAnno(Integer annoInizio, Integer annoFine, Pageable pageable);
}

package luigialbino.cinema.service.impl;

import luigialbino.cinema.exception.GestioneException;
import luigialbino.cinema.model.dto.PageResponseDTO;
import luigialbino.cinema.mapper.FilmMapper;
import luigialbino.cinema.model.dto.FilmDTO;
import luigialbino.cinema.model.entity.Film;
import luigialbino.cinema.repository.FilmRepository;
import luigialbino.cinema.service.api.IFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmService implements IFilmService {

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private FilmMapper filmMapper;

    @Override
    public PageResponseDTO<FilmDTO> getAll(Pageable pageable){
        Page<Film> listaFilm = filmRepository.findAll(pageable);
        List<FilmDTO> listaFilmDTO = filmMapper.convertToDTOList(listaFilm.getContent());
        return new PageResponseDTO<>(listaFilm, listaFilmDTO);
    }

    @Override
    public FilmDTO getById(Long id){
        Optional<Film> filmDaCercare = filmRepository.findById(id);

        if (filmDaCercare.isEmpty()){
            throw GestioneException.notFound("Nessun film presente con id: " + id);
        } else {
            return filmMapper.toDto(filmDaCercare.get());
        }
    }

    @Override
    public FilmDTO createFilm(FilmDTO filmDTO){
        boolean exists = filmRepository.existsByTitoloIgnoreCase(filmDTO.getTitolo());

        if(exists){
            Film film = filmRepository.findByTitoloIgnoreCase(filmDTO.getTitolo());
            Long idFilm = film.getId();
            throw GestioneException.conflict("Film già presente a catalogo con id: " + idFilm);
        } else{
            Film film = filmMapper.toEntity(filmDTO);
            Film nuovoFilm = filmRepository.save(film);
            return filmMapper.toDto(nuovoFilm);
        }
    }

    @Override
    public FilmDTO aggiornaFilm(Long id, FilmDTO filmDTO){
        Optional<Film> filmDaAggiornare = filmRepository.findById(id);

        if(filmDaAggiornare.isPresent()){

            if(filmDTO.getTitolo() != null){
                filmDaAggiornare.get().setTitolo(filmDTO.getTitolo());
            }
            if(filmDTO.getDurata() != null){
                filmDaAggiornare.get().setDurata(filmDTO.getDurata());
            }
            if(filmDTO.getDescrizione() != null){
                filmDaAggiornare.get().setDescrizione(filmDTO.getDescrizione());
            }
            if(filmDTO.getDataUscita() != null){
                filmDaAggiornare.get().setDataUscita(filmDTO.getDataUscita());
            }
            if(filmDTO.getRegista() != null){
                filmDaAggiornare.get().setRegista(filmDTO.getRegista());
            }

            Film filmAggiornato = filmRepository.save(filmDaAggiornare.get());
            return filmMapper.toDto(filmAggiornato);
        } else {
            throw GestioneException.notFound("Nessun film presente con id: " + id);
        }
    }

    @Override
    public void deleteFilm(Long id){
        Optional<Film> filmDaEliminare = filmRepository.findById(id);

        if(filmDaEliminare.isPresent()){
            filmRepository.delete(filmDaEliminare.get());
        }else{
            throw GestioneException.notFound("Nessun film presente con id: " + id);
        }
    }

    @Override
    public PageResponseDTO<FilmDTO> cercaPerRegistaEAnno(String regista, Integer annoInizio, Integer annoFine, Pageable pageable){
        if (annoInizio != null && annoFine != null && annoInizio > annoFine){
            throw GestioneException.conflict("Anno Inizio non può essere maggiore di Anno Fine");
        }

        Page<Film> listaFilm = filmRepository.cercaPerRegistaEAnno(regista, annoInizio, annoFine, pageable);

        if(listaFilm.isEmpty()){
            throw GestioneException.notFound("Nessun film presente");
        }

        List<FilmDTO> listaFilmDTO = filmMapper.convertToDTOList(listaFilm.getContent());
        return new PageResponseDTO<>(listaFilm, listaFilmDTO);
    }

    @Override
    public PageResponseDTO<FilmDTO> cercaFilmPerAnno(Integer annoInizio, Integer annoFine, Pageable pageable){
        if(annoInizio == null){
            annoInizio = filmRepository.findMinDataUscita();
        }
        if(annoFine == null){
            annoFine = filmRepository.findMaxDataUscita();
        }
        if (annoInizio > annoFine){
            throw GestioneException.conflict("Anno inizio maggiore di anno fine");
        }

        Page<Film> listaFilm = filmRepository.findByDataUscitaBetween(annoInizio, annoFine, pageable);
        List<FilmDTO> listaFilmDTO = filmMapper.convertToDTOList(listaFilm.getContent());

        return new PageResponseDTO<>(listaFilm, listaFilmDTO);
    }
}

package luigialbino.cinema.service.impl;

import luigialbino.cinema.exception.GestioneException;
import luigialbino.cinema.model.dto.PageResponseDTO;
import luigialbino.cinema.model.dto.PrenotazioneDTO;
import luigialbino.cinema.model.dto.PrenotazioneResponseDTO;
import luigialbino.cinema.model.entity.Prenotazione;
import luigialbino.cinema.repository.FilmRepository;
import luigialbino.cinema.repository.PrenotazioneRepository;
import luigialbino.cinema.repository.SalaRepository;
import luigialbino.cinema.repository.UtenteRepository;
import luigialbino.cinema.service.api.IPrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrenotazioneService implements IPrenotazioneService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private SalaRepository salaRepository;

    @Override
    public PageResponseDTO<PrenotazioneResponseDTO> leMiePrenotazioni(Pageable pageable){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        Long utendeID = utenteRepository.findByEmail(email).get().getId();

        Page<Prenotazione> listaPrenotazioni = prenotazioneRepository.findByUtenteId(utendeID, pageable);

        if (listaPrenotazioni.isEmpty()){
            throw GestioneException.notFound("Nessuna prenotazione");
        }

        List<PrenotazioneResponseDTO> listaPrenotazioniDTO = new ArrayList<>();

        for (Prenotazione pren : listaPrenotazioni.getContent()){
            PrenotazioneResponseDTO prenDTO = new PrenotazioneResponseDTO();
            prenDTO.setNomeFilm(pren.getProiezione().getFilm().getTitolo());
            prenDTO.setNomeSala(pren.getProiezione().getSala().getNome());
            prenDTO.setNumPosti(pren.getPostiPrenotati());
            prenDTO.setDataOraPrenotazione(pren.getProiezione().getDataOraInizio());
            listaPrenotazioniDTO.add(prenDTO);
        }

        return new PageResponseDTO<>(listaPrenotazioni, listaPrenotazioniDTO);
    }
}

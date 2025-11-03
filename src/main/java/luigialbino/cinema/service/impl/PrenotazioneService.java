package luigialbino.cinema.service.impl;

import luigialbino.cinema.exception.GestioneException;
import luigialbino.cinema.model.dto.PageResponseDTO;
import luigialbino.cinema.model.dto.PrenotazioneRequestDTO;
import luigialbino.cinema.model.dto.PrenotazioneResponseDTO;
import luigialbino.cinema.model.entity.Prenotazione;
import luigialbino.cinema.model.entity.Proiezione;
import luigialbino.cinema.model.entity.Utente;
import luigialbino.cinema.repository.*;
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

    @Autowired
    private ProiezioneRepository proiezioneRepository;


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

    @Override
    public PrenotazioneResponseDTO creaPrenotazione(PrenotazioneRequestDTO dto){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        Utente utente = utenteRepository.findByEmail(email).get();
        Proiezione proiezione = proiezioneRepository.findById(dto.getIdProiezione())
                .orElseThrow(() -> GestioneException.notFound("Nessuna proiezione con id: " + dto.getIdProiezione()));

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setUtente(utente);
        prenotazione.setProiezione(proiezione);
        prenotazione.setPostiPrenotati(dto.getPostiPrenotati());

        checkPosti(proiezione, dto.getPostiPrenotati());

        Prenotazione salvata = prenotazioneRepository.save(prenotazione);

        PrenotazioneResponseDTO response = new PrenotazioneResponseDTO();
        response.setNomeFilm(salvata.getProiezione().getFilm().getTitolo());
        response.setNomeSala(salvata.getProiezione().getSala().getNome());
        response.setNumPosti(salvata.getPostiPrenotati());
        response.setDataOraPrenotazione(salvata.getProiezione().getDataOraInizio());

        return response;
    }

    private void checkPosti(Proiezione proiezione, int postiRichiesti){
        Integer postiGiaPrenotati = prenotazioneRepository.sumPostiPrenotatiByProiezioneId(proiezione.getId());

        if (postiGiaPrenotati == null) {
            postiGiaPrenotati = 0;
        }

        int postiDisponibili = proiezione.getSala().getNumeroPosti() - postiGiaPrenotati;

        if (postiRichiesti > postiDisponibili) {
            throw GestioneException.badRequest("Posti insufficienti. Disponibili: " + postiDisponibili);
        }

        if (postiRichiesti <= 0) {
            throw GestioneException.badRequest("Il numero di posti deve essere maggiore di zero");
        }
    }
}

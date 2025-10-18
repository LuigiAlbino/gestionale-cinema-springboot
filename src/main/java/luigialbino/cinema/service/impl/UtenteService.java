package luigialbino.cinema.service.impl;

import luigialbino.cinema.exception.GestioneException;
import luigialbino.cinema.mapper.UtenteMapper;
import luigialbino.cinema.model.dto.ModificaPasswordDTO;
import luigialbino.cinema.model.dto.PageResponseDTO;
import luigialbino.cinema.model.dto.UtenteDTO;
import luigialbino.cinema.model.entity.Ruolo;
import luigialbino.cinema.model.entity.Utente;
import luigialbino.cinema.repository.UtenteRepository;
import luigialbino.cinema.service.api.IUtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtenteService implements IUtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private UtenteMapper utenteMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public PageResponseDTO<UtenteDTO> getAll(Pageable pageable){
        Page<Utente> listaUtenti = utenteRepository.findAll(pageable);
        if(listaUtenti.isEmpty()){
            throw GestioneException.notFound("Nessun utente presente");
        }
        List<UtenteDTO> listaUtentiDTO = utenteMapper.convertToDTOList(listaUtenti.getContent());
        return new PageResponseDTO<>(listaUtenti, listaUtentiDTO);
    }

    @Override
    public UtenteDTO getUtenteById(Long id){
        Optional<Utente> utenteDaCercare = utenteRepository.findById(id);

        if (utenteDaCercare.isEmpty()){
            throw GestioneException.notFound("Nessun utente presente con id: " + id);
        } else {
            return utenteMapper.toDto(utenteDaCercare.get());
        }
    }

    @Override
    public UtenteDTO createUtente(UtenteDTO utenteDTO){
        boolean exists = utenteRepository.existsByEmailIgnoreCase(utenteDTO.getEmail());

        if(exists){
            Utente utente = utenteRepository.findByEmailIgnoreCase(utenteDTO.getEmail());
            Long idUtente = utente.getId();
            throw GestioneException.conflict("Utente già presente con id: " + idUtente);
        } else{
            Utente utente = utenteMapper.toEntity(utenteDTO);
            utente.setIsFirstAccess(true);
            utente.setPassword(passwordEncoder.encode("password"));
            if(utente.getRuolo() == null){
                utente.setRuolo(Ruolo.valueOf(Ruolo.ROLE_FIRSTACCESS.toString()));
            }
            Utente nuovoUtente = utenteRepository.save(utente);
            return utenteMapper.toDto(nuovoUtente);
        }
    }

    @Override
    public UtenteDTO aggiornaUtente(Long id, UtenteDTO utenteDTO){
        Utente utente = utenteRepository.findById(id)
                .orElseThrow(()  -> GestioneException.notFound("Nessun utente presente con id: " + id));

        utenteMapper.updateEntityFromDto(utenteDTO, utente);
        Utente utenteAggiornato = utenteRepository.save(utente);
        return utenteMapper.toDto(utenteAggiornato);
    }

    @Override
    public void deleteUtente(Long id){
        Utente utente = utenteRepository.findById(id)
                .orElseThrow(()  -> GestioneException.notFound("Nessun utente presente con id: " + id));

        utenteRepository.delete(utente);
    }

    public void modificaPasswordPrimoAccesso(ModificaPasswordDTO request){
        Utente utente = utenteRepository.findByEmailIgnoreCase(request.getEmail());
        if(utente == null){
            throw GestioneException.notFound("Email non presente");
        }

        if (!passwordEncoder.matches(request.getVecchiaPassword(), utente.getPassword())){
            throw GestioneException.badRequest("Password errata");
        }

        String nuovaPassword = passwordEncoder.encode(request.getNuovaPassword());
        utente.setPassword(nuovaPassword);
        utente.setIsFirstAccess(false);
        utente.setRuolo(Ruolo.valueOf(Ruolo.ROLE_USER.toString()));
        utenteRepository.save(utente);
    }

}

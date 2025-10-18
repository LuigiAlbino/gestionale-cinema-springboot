package luigialbino.cinema.mapper;

import luigialbino.cinema.model.dto.PrenotazioneDTO;
import luigialbino.cinema.model.entity.Prenotazione;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UtenteMapper.class, ProiezioneMapper.class})
public interface PrenotazioneMapper {

    @Mapping(source = "utente", target = "utente")
    @Mapping(source = "proiezione", target = "proiezione")
    PrenotazioneDTO toDto(Prenotazione prenotazione);

    @Mapping(source = "utente", target = "utente")
    @Mapping(source = "proiezione", target = "proiezione")
    Prenotazione toEntity(PrenotazioneDTO dto);
}

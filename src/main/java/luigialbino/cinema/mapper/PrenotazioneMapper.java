package luigialbino.cinema.mapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UtenteMapper.class, ProiezioneMapper.class})
public interface PrenotazioneMapper {
}

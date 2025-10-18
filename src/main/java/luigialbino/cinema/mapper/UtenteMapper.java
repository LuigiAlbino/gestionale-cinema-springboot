package luigialbino.cinema.mapper;

import luigialbino.cinema.model.dto.FilmDTO;
import luigialbino.cinema.model.dto.UtenteDTO;
import luigialbino.cinema.model.entity.Film;
import luigialbino.cinema.model.entity.Utente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UtenteMapper {

    //@Mapping(target = "password", ignore = true)
    UtenteDTO toDto(Utente utente);

    @Mapping(target = "password", ignore = true)
    Utente toEntity(UtenteDTO dto);

    List<UtenteDTO> convertToDTOList(List<Utente> listaUtenti);

    void updateEntityFromDto(UtenteDTO dto, @MappingTarget Utente entity);
}

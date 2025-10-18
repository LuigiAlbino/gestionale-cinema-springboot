package luigialbino.cinema.mapper;

import luigialbino.cinema.model.dto.ProiezioneDTO;
import luigialbino.cinema.model.entity.Proiezione;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {FilmMapper.class, SalaMapper.class})
public interface ProiezioneMapper {

    @Mapping(source = "film", target = "film")
    @Mapping(source = "sala", target = "sala")
    ProiezioneDTO toDto(Proiezione proiezione);

    @Mapping(source = "film", target = "film")
    @Mapping(source = "sala", target = "sala")
    Proiezione toEntity(ProiezioneDTO dto);
}

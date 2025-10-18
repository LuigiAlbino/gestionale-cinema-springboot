package luigialbino.cinema.mapper;

import luigialbino.cinema.model.dto.SalaDTO;
import luigialbino.cinema.model.entity.Sala;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SalaMapper {
    SalaDTO toDto(Sala sala);
    Sala toEntity(SalaDTO dto);
}

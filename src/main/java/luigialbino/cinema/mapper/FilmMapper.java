package luigialbino.cinema.mapper;

import luigialbino.cinema.model.dto.FilmDTO;
import luigialbino.cinema.model.entity.Film;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FilmMapper {
    FilmDTO toDto(Film film);
    Film toEntity(FilmDTO dto);
    List<FilmDTO> convertToDTOList(List<Film> listaFilm);
}

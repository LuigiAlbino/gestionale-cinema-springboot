package luigialbino.cinema.exception;

import luigialbino.cinema.model.dto.ErrorResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(GestioneException.class)
    public ResponseEntity<ErrorResponseDTO> hendleException(GestioneException ex){
        ErrorResponseDTO errore = new ErrorResponseDTO(
                ex.getStatus().value(),
                ex.getMessage()
        );

        return ResponseEntity.status(ex.getStatus()).body(errore);

    }
}

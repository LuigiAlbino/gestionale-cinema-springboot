package luigialbino.cinema.exception;

import org.springframework.http.HttpStatus;

public class GestioneException extends RuntimeException{
    private final HttpStatus status;

    public GestioneException(String messaggio, HttpStatus status) {
        super(messaggio);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public static GestioneException notFound(String messaggio){
        return new GestioneException(messaggio, HttpStatus.NOT_FOUND);
    }

    public static GestioneException badRequest(String messaggio){
        return new GestioneException(messaggio, HttpStatus.BAD_REQUEST);
    }

    public static GestioneException conflict(String messaggio){
        return new GestioneException(messaggio, HttpStatus.CONFLICT);
    }

    public static GestioneException forbidden(String messaggio){
        return new GestioneException(messaggio, HttpStatus.FORBIDDEN);
    }
}

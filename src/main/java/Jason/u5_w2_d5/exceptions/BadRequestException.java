package Jason.u5_w2_d5.exceptions;

import org.springframework.validation.ObjectError;

import java.util.List;

// Eccezione per richieste non valide (400). Può contenere lista di errori di validazione
public class BadRequestException extends RuntimeException {
    // Lista di errori di validazione
    private List<ObjectError> errorsList;

    // Costruttore semplice con messaggio
    public BadRequestException(String message) {
        super(message);
    }

    // Costruttore con lista di errori (usato per validazione)
    public BadRequestException(List<ObjectError> errorsList) {
        super("Errori nel body");
        this.errorsList = errorsList;
    }

    // Restituisce la lista di errori
    public List<ObjectError> getErrorsList() {
        return errorsList;
    }
}

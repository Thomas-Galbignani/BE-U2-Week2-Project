package thomasgalbignani.BE_U2_Week2_Project.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID id) {
        super("Il dipendente id " + id + " non è stato trovato!");
    }
}

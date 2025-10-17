package thomasgalbignani.BE_U2_Week2_Project.payloads;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PrenotazioneDTO {

    @NotNull(message = "L'ID del dipendente è obbligatorio")
    private UUID dipendenteId;

    @NotNull(message = "L'ID del viaggio è obbligatorio")
    private UUID viaggioId;

    private String note;

    @Size(max = 100, message = "La preferenza non può superare i 100 caratteri")
    private String preferenzaVoloOAlloggio;
}
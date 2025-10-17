package thomasgalbignani.BE_U2_Week2_Project.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import thomasgalbignani.BE_U2_Week2_Project.entities.StatoViaggio;

import java.time.LocalDate;

@Getter
public class ViaggioDTO {
    @NotBlank(message = "La destinazione è obbligatoria")
    private String destinazione;

    @NotNull(message = "La data del viaggio è obbligatoria")
    private LocalDate dataViaggio;

    @NotNull(message = "Lo stato del viaggio è obbligatorio")
    private StatoViaggio stato;
}
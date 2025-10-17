package thomasgalbignani.BE_U2_Week2_Project.payloads;

import java.time.LocalDateTime;

public record ErrorsDTO(String message, LocalDateTime timestamp) {
}
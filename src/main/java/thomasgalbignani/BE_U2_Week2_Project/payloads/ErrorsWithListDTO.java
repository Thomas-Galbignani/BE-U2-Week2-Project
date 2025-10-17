package thomasgalbignani.BE_U2_Week2_Project.payloads;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorsWithListDTO(String message,
                                LocalDateTime timestamp,
                                List<String> errorsList) {
}

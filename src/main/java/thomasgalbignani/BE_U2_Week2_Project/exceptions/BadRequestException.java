package thomasgalbignani.BE_U2_Week2_Project.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
package balancefy.api.domain.exceptions;

public class NotFoundException extends Exception{
    public NotFoundException(String message) {
        super(message);
    }
}
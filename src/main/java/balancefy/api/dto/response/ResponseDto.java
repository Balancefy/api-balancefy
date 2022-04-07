package balancefy.api.dto.response;

public abstract class ResponseDto {
    private String message;

    public ResponseDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

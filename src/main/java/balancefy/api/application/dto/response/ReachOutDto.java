package balancefy.api.application.dto.response;

public class ReachOutDto extends ResponseDto{
    Double value;

    public ReachOutDto(String message, Double value) {
        super(message);
        this.value = value;
    }

    public ReachOutDto(String message) {
        super(message);
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}

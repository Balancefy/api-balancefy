package balancefy.api.application.dto.response;

import balancefy.api.resources.entities.Objetivo;

import java.util.List;

public class ListObjetivoResponseDto extends ResponseDto{
    List<Objetivo> data;

    public ListObjetivoResponseDto(String message, List<Objetivo> data) {
        super(message);
        this.data = data;
    }

    public ListObjetivoResponseDto(String message) {
        super(message);
    }

    public List<Objetivo> getData() {
        return data;
    }

    public void setData(List<Objetivo> data) {
        this.data = data;
    }
}

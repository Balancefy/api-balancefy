package balancefy.api.application.dto.response;

import balancefy.api.resources.entities.Comentario;
import balancefy.api.resources.entities.Objetivo;

import java.util.ArrayList;
import java.util.List;

public class ListaComentarioResponseDto extends ResponseDto{
    private List<Comentario> list;

    public ListaComentarioResponseDto(String message, List<Comentario> list) {
        super(message);
        this.list = list;
    }

    public ListaComentarioResponseDto(String message) {
        super(message);
    }

    public void setList(List<Comentario> list) {
        this.list = list;
    }

    public List<Comentario> getList() {
        return list;
    }
}

package balancefy.api.application.dto.response;

import java.util.ArrayList;
import java.util.List;

public class ListaUsuarioResponseDto extends ResponseDto{
    private List<UsuarioResponseDto> list;

    public ListaUsuarioResponseDto(List<UsuarioResponseDto> list) {
        super("SUCESSO");
        this.list = list;
    }

    public ListaUsuarioResponseDto(Exception ex) {
        super(ex.getMessage());
        this.list = new ArrayList<>();
    }

    public List<UsuarioResponseDto> getList() {
        return list;
    }
}

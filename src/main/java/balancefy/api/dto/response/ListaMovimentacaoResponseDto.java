package balancefy.api.dto.response;

import java.util.ArrayList;
import java.util.List;

public class ListaMovimentacaoResponseDto extends ResponseDto {
    private List<MovimentacaoResponseDto> list;


    public ListaMovimentacaoResponseDto( List<MovimentacaoResponseDto> list) {
        super("SUCESSO");
        this.list = list;
    }

    public ListaMovimentacaoResponseDto(Exception ex) {
        super(ex.getMessage());
        this.list = new ArrayList<>();
    }
}

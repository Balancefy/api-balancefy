package balancefy.api.dto.response;

import java.util.ArrayList;
import java.util.List;

public class ListaDicaResponseDto extends ResponseDto {
    private List<DicaResponseDto> list;

    public ListaDicaResponseDto( List<DicaResponseDto> list) {
        super("SUCESSO");
        this.list = list;
    }

    public ListaDicaResponseDto(Exception ex) {
        super(ex.getMessage());
        this.list = new ArrayList<>();
    }

    public List<DicaResponseDto> getList() {
        return list;
    }
}

package balancefy.api.application.dto.response;

import java.util.List;

public class ListBiggestExpensesDto extends ResponseDto {

    public ListBiggestExpensesDto(String message) {
        super(message);
    }

    public ListBiggestExpensesDto(String message, List<BiggestExpensesDto> list) {
        super(message);
        this.list = list;
    }

    public void setList(List<BiggestExpensesDto> list) {
        this.list = list;
    }

    public List<BiggestExpensesDto> getList() {
        return list;
    }

    List<BiggestExpensesDto> list;

}

package balancefy.api.application.dto.response;

import java.util.ArrayList;
import java.util.List;

public class ListaFeedTopicoResponse extends ResponseDto {
    private List<FeedTopicoResponseDto> list;

    public ListaFeedTopicoResponse( List<FeedTopicoResponseDto> list) {
        super("SUCESSO");
        this.list = list;
    }

    public ListaFeedTopicoResponse(Exception ex) {
        super(ex.getMessage());
        this.list = new ArrayList<>();
    }

    public List<FeedTopicoResponseDto> getList() {
        return list;
    }

    public void setList(List<FeedTopicoResponseDto> list) {
        this.list = list;
    }
}

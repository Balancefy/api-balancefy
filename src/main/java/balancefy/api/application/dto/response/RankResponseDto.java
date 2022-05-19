package balancefy.api.application.dto.response;

import java.util.List;

public class RankResponseDto extends ResponseDto {

    private List<ContaRankResponseDto> rank;

    public RankResponseDto(List<ContaRankResponseDto> list) {
        super("Sucesso");
        this.rank = list;
    }

    public RankResponseDto(Exception ex) {
        super(ex.getMessage());
    }

    public List<ContaRankResponseDto> getRank() {
        return rank;
    }
}
